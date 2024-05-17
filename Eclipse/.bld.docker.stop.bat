call .bld.setup.bat
for /f "delims=" %%x in ('dir /b /ad') do (
  if exist "%%x\Dockerfile" (
    docker stop %%x
    docker rmi %%x
  )
)

