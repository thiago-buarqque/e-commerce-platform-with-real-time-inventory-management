files=$( (git diff --name-only main...HEAD; git status --porcelain | cut -b4-) | sort -u )

for file in $files; do
    if [[ $file == *.java ]]; then
        echo "Formatting file '$file'"
        java -jar ./google-java-format-*-all-deps.jar -r "../$file"
    fi
done
