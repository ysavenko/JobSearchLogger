call .bld.setup.bat
for /f "delims=" %%x in ('dir /b /ad') do (
  if exist "%%x\Dockerfile" (
    docker stop %%x
    docker rmi %%x
    setlocal enableDelayedExpansion
    for /f "tokens=1,* delims== " %%i in (docker.properties) do (
      set PROPERTY_NAME=%%i
      for /f "tokens=1,2 delims=." %%a in ("!PROPERTY_NAME!") do (
        set PROPERTY_NAME_PREFIX=%%a      
        set "PROPERTY_NAME_PREFIX=!PROPERTY_NAME_PREFIX: =!"
        set PROPERTY_NAME=%%b
      )  

      if "!PROPERTY_NAME_PREFIX!"=="%%x" (
        if "!PROPERTY_NAME!"=="ARG_EXPOSE_PORT" (set EXPOSE_PORT=%%j)
        if "!PROPERTY_NAME!"=="PORT" (set PORT_MAPPING=%%j)  
        set PROPERTY_NAME_START=!PROPERTY_NAME:~0,4!
        if "!PROPERTY_NAME_START!" == "ARG_" (set BUILD_ARGS=!BUILD_ARGS! --build-arg !PROPERTY_NAME!=%%j)
      )
    ) 

    if NOT "!PORT_MAPPING!"=="" (set PORT_MAPPING=-p !PORT_MAPPING!:!EXPOSE_PORT!) 

    docker build -f %%x/Dockerfile --build-arg ARG_JAR_FILE=%%x/target/%%x-%ver%.jar !BUILD_ARGS! -t %%x .
    docker run !PORT_MAPPING! --mount type=volume,src=%%x,target=//etc/myData -d --name %%x --rm %%x
    docker network connect myNetwork %%x
    setlocal disableDelayedExpansion
  )
)

