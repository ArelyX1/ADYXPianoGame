Referencias tomadas
https://github.com/JiwooL0920/DynamicBeat
https://blog.naver.com/ndb796/220997028385

Build y Contenerización

- Compilar y crear JAR localmente (Linux / macOS):

```bash
./build.sh
```

- Compilar y crear JAR localmente (Windows PowerShell):

```powershell
.\build.bat
```

- Construir imagen Docker:

```bash
docker build -t adyxpianogame:latest .
```

- Ejecutar contenedor:

```bash
docker run --rm -it adyxpianogame:latest
```

Notas:
- Los recursos (`images/` y `music/`) se incluyen dentro del JAR y se cargan desde el classpath.
- Si usas un IDE (IntelliJ/Eclipse/VSCode) puedes exportar el JAR ejecutable con la clase principal `dynamic_beat_17.Main`.
