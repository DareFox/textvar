# textvar
*"I'm sure someone has already made such a program, but I can't find it"*
### Generate all possible unique text variants with nesting 

![terminal2](https://user-images.githubusercontent.com/47672780/213028782-6566b29f-40a5-4bd5-a218-a360f2ebba05.gif)


### Arguments
```bash
--help                          Show this help message
--text                          Text with template. Required
--borderLeft                    Character representing start of the template border. Default is [
--borderRight                   Character representing end of the template border. Default is ]
--separator                     Separator of template. Default is ;
--border                        Two characters representing start and end of the border. "--border {}" equals to "--borderLeft { --borderRight }"
```

### Download
[Here](https://github.com/DareFox/textvar/releases/latest)

### Build from source
[![Create native distributions](https://github.com/DareFox/textvar/actions/workflows/nativeBuild.yml/badge.svg)](https://github.com/DareFox/textvar/actions/workflows/nativeBuild.yml)
- Clone repo
- Open terminal and enter `./gradlew build` or `./gradlew.bat build` if you on Windows
- Binary file will be in `./build/bin/native/releaseExecutable/`

### License
MIT
