# Инструкция по настройке проекта

## Проблема с JDK

Если вы видите ошибку:
```
It is not possible to use the currently selected Gradle JVM for the operation.
The installation from C:\Program Files\Java\jdk-20 will be used instead.
```

Это означает, что используется JDK 20, а проект настроен на Java 17.

## Решение

### Вариант 1: Использовать Java 17 (Рекомендуется)

1. Установите JDK 17 (если еще не установлен):
   - Скачайте с https://adoptium.net/temurin/releases/?version=17
   - Или используйте OpenJDK 17

2. В IntelliJ IDEA:
   - File → Settings (или Ctrl+Alt+S)
   - Build, Execution, Deployment → Build Tools → Gradle
   - В поле "Gradle JVM" выберите Java 17 (или Project SDK, если он настроен на Java 17)

3. Также настройте Project SDK:
   - File → Project Structure (или Ctrl+Alt+Shift+S)
   - Project → SDK: выберите Java 17
   - Project → Language level: выберите 17

### Вариант 2: Изменить проект на Java 20 (если хотите использовать Java 20)

Если вы хотите использовать Java 20, нужно изменить конфигурацию:

1. В `build.gradle.kts` измените:
   ```kotlin
   java {
       sourceCompatibility = JavaVersion.VERSION_20
   }
   ```

2. И в `kotlinOptions`:
   ```kotlin
   jvmTarget = "20"
   ```

3. То же самое нужно сделать в `hobby-changer/build.gradle.kts`

## Запуск Gradle Wrapper

После настройки JDK, выполните:

```bash
./gradlew build
```

или в Windows:

```bash
gradlew.bat build
```

Это скачает Gradle 8.5 (указан в `gradle/wrapper/gradle-wrapper.properties`) и все зависимости.

## Если проблемы остаются

1. Очистите кэш Gradle:
   ```bash
   ./gradlew clean --refresh-dependencies
   ```

2. В IntelliJ IDEA:
   - File → Invalidate Caches / Restart
   - Выберите "Invalidate and Restart"

3. Удалите папки `.gradle` и `build` в корне проекта и в `hobby-changer`, затем попробуйте снова.


