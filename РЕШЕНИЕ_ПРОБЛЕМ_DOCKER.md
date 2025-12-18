# Решение проблем с Docker

## Ошибка: "unable to get image" или "cannot find the file specified"

Эта ошибка означает, что **Docker Desktop не запущен** или не установлен.

### Решение 1: Запустить Docker Desktop

1. Найдите **Docker Desktop** в меню Пуск Windows
2. Запустите приложение
3. Дождитесь, пока Docker Desktop полностью запустится (в трее появится иконка Docker)
4. Убедитесь, что иконка Docker в трее показывает статус "Running" (зеленая)

### Решение 2: Проверить, что Docker работает

Откройте новый терминал и выполните:

```bash
docker --version
docker ps
```

Если команды работают без ошибок - Docker запущен правильно.

### Решение 3: Перезапустить Docker Desktop

Если Docker Desktop уже запущен, но ошибка все равно появляется:

1. Правой кнопкой на иконку Docker в трее → **Quit Docker Desktop**
2. Подождите 10 секунд
3. Запустите Docker Desktop снова
4. Дождитесь полного запуска
5. Попробуйте снова: `docker-compose up -d`

### Решение 4: Установить Docker Desktop (если не установлен)

1. Скачайте Docker Desktop для Windows:
   - https://www.docker.com/products/docker-desktop/
2. Установите приложение
3. Перезагрузите компьютер (если потребуется)
4. Запустите Docker Desktop
5. Следуйте инструкциям по первому запуску

## Проверка после запуска Docker

После того как Docker Desktop запущен, проверьте:

```bash
# Проверить версию Docker
docker --version

# Проверить версию Docker Compose
docker-compose --version

# Посмотреть запущенные контейнеры
docker ps

# Посмотреть все контейнеры (включая остановленные)
docker ps -a
```

## После запуска Docker Desktop

Когда Docker Desktop запущен, выполните:

```bash
docker-compose up -d
```

Вы должны увидеть что-то вроде:

```
[+] Running 3/3
 ✔ Container practika3-zookeeper    Started
 ✔ Container practika3-postgres     Started
 ✔ Container practika3-kafka        Started
```

## Если проблемы остаются

1. **Проверьте права доступа:**
   - Убедитесь, что вы запускаете терминал от имени администратора (если требуется)

2. **Проверьте настройки Docker Desktop:**
   - Откройте Docker Desktop
   - Settings → General → убедитесь, что "Use WSL 2 based engine" включен (если используете WSL)

3. **Перезагрузите компьютер:**
   - Иногда помогает перезагрузка после установки Docker

4. **Проверьте антивирус:**
   - Некоторые антивирусы блокируют Docker. Попробуйте временно отключить или добавить исключение для Docker


