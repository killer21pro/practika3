# Решение проблемы с подключением к PostgreSQL

## Проблема
```
org.postgresql.util.PSQLException: ОШИБКА: аутентификация пользователя "student" не удалась
```

## ✅ Решение

### Шаг 1: Пересоздать контейнеры с чистой БД

```bash
docker-compose down -v
docker-compose up -d
```

Флаг `-v` удаляет volumes (данные БД), что гарантирует чистую инициализацию.

### Шаг 2: Подождать инициализации PostgreSQL

PostgreSQL должен полностью инициализироваться. Подождите 5-10 секунд после `docker-compose up -d`.

### Шаг 3: Проверить подключение

```bash
docker exec practika3-postgres psql -U student -d studentdb -c "SELECT version();"
```

Если команда выполняется без ошибок - подключение работает.

### Шаг 4: Запустить приложение

```bash
gradlew.bat bootRun
```

---

## Проверка настроек

Убедитесь, что в `src/main/resources/application.yml` указано:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/studentdb
    username: student
    password: student123
```

И в `docker-compose.yml`:

```yaml
environment:
  POSTGRES_DB: studentdb
  POSTGRES_USER: student
  POSTGRES_PASSWORD: student123
```

---

## Если проблема сохраняется

1. **Проверить логи PostgreSQL:**
   ```bash
   docker-compose logs postgres
   ```

2. **Перезапустить только PostgreSQL:**
   ```bash
   docker-compose restart postgres
   ```

3. **Проверить статус контейнеров:**
   ```bash
   docker-compose ps
   ```

Все контейнеры должны быть в статусе "Up" и "healthy".
