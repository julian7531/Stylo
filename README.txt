Compiling code:
        Bash (linux):
                javac -d out $(find . -name "*.java")

                After comilation:
                        java -cp out Stylo.Stylo

        Powershell (windows):
                Remove-Item -Recurse -Force out -ErrorAction SilentlyContinue
                New-Item -ItemType Directory -Name out

                javac -d out (Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })

                After compilation:
                        java -cp out Stylo.Stylo



Running a script:
        bash (linux):
                java -cp out Stylo.Stylo filename.stylo


        Powershell (windows):
                java -cp out Stylo.Stylo test.stylo
