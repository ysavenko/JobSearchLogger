call .bld.setup.bat

for %%x in (%projects.all%) do (
  cd %%x
   mvn clean install
  cd ..
)


call .bld.setup.bat
for /f "delims=" %%x in ('dir /b /ad') do (
  if exist "%%x\Dockerfile" (
    cd %%x/target
    rem start "%%x" java -jar %%x-%ver%.jar --users.host=http://google.com:8080
    cd ../..
  )
)



