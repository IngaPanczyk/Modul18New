call runcrud
if "%ERRORLEVEL%" == "0" goto openbowser
echo.
echo runcrud has errors - breaking work
goto fail

:openbowser
start http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo Cannot open url adress
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.