# P2P мессенджер
Мессенджер в локальной сети с шифрованием.


## Технологии
| Компонент     | Технология |
|---------------|-------|
| **Язык**      | Java 25 |
| **Сборщик**   | Maven |
| **Фреймворк** | Spring|



## Требования:
- Java25+
- Maven 3


## Установка
### Клонирование репозитория
```bash
  git clone https://github.com/prophetbrochure/p2p_messenger
```

### Запуск
## Запуск сервиса UserService
```bash
  mvn clean package -pl UserService -am или чёт такое
```
## Запуск сервиса EventCatalog
```bash
  mvn clean package -pl EventCatalogService -am или чёт такое
```
## Запуск сервиса PaymentService
```bash
  mvn clean package -pl PaymentService -am или чёт такое
```


## Архитектура
```
├── pom.xml
├── README.md
├───src
│   ├───main
│   │   ├───java
│   │   │   ├───PaymentService              #
│   │   │   │   ├───                        #
│   │   │   │   └───                        #
│   │   │   ├───EventCatalogService         #
│   │   │   │   ├───Controller              #
│   │   │   │   ├───DataBase                #
│   │   │   │   ├───Repositories            #
│   │   │   │   └───Service                 #
│   │   │   └───UserService                 #
│   │   │       ├───Configuration           #
│   │   │       ├───Controllers             #
│   │   │       ├───db                      #
│   │   │       ├───Repostories             #
│   │   │       └───Services                #
│   │   └───resources                       # 
│   └───test                                # Тесты)
└── target                                  # Исполняемые файлы
```


## Авторы
**Дмитриев Андрей**
- <a href="https://github.com/prophetbrochure">Github</a>

**Домников Владислав**
- <a href="https://github.com/Kkenos">Github</a>
