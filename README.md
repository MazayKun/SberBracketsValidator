# Brackets validator service

Minimal application for Sber Clean Code competition

## Requirements

For running this app you need:
- JDK 17
- Docker (Optionally)

## Running locally

There is several ways to run this application on local machine. 
The simplest way is to run `ru.mikheev.kirill.bracketsvalidator.BracketsValidatorApplication` class from your IDE.

Alternatively you can use the `gradlew`:
```shell
./gradlew bootRun
```

In both cases you can specify the server port and log level for the application by
setting corresponding environment variables:
```shell
BRACKETS_VALIDATOR_SERVICE_PORT=8080
BRACKETS_VALIDATOR_SERVICE_LOG_LEVEL=INFO
```

Also, you can run application as docker container if you have it installed.
There is two ways to start this application in docker.
First, you can build image and start instance by this commands:
```shell
./gradlew clean build
docker build . -t bracketsvalidationservice
docker run -p 8080:8080 --name bracketsvalidationservice bracketsvalidationservice
```
You can also specify log level for container by providing environment variable to it 
with `-e BRACKETS_VALIDATOR_SERVICE_LOG_LEVEL=INFO`

The second variant of docker usage is to run it by docker-compose.
All you need to do is build project and run docker-compose by following command:
```shell
./gradlew clean build
docker-compose up -d
```
If you want to specify application port and log level, you should open `.env` file 
and change corresponding environment variables to required values.

## API

### Validate brackets sequence request
Service validate received text by following criteria:
- Each open bracket has corresponding close bracket (3 bracket types allowed: `()[]{}`)
- Each close bracket has corresponding open bracket
- Between open and close bracket always exists content, text or another bracket pair (with content in it)

### Request
`POST /api/checkBrackets`

    curl --location 'localhost:8080/api/checkBrackets' \
    --header 'Content-Type: application/json' \
    --data '{
                "text": "Вчера я отправился в поход в лес (это мое любимое место для отдыха) вместе с друзьями. 
                        Мы выбрали маршрут, который проходил через горные потоки и поля (для разнообразия). 
                        В начале пути погода была отличной, солнце светило ярко, и птицы радостно пели. 
                        Однако, когда мы подошли ближе к вершине горы, небо стало покрываться облаками, (как будто природа готовила нам небольшой сюрприз). 
                        Несмотря на это, виды были захватывающими, особенно когда мы достигли высшей точки и увидели прекрасный вид на долину (я почувствовал, что все усилия стоили того)."
            }'

### Response

    HTTP/1.1 200 OK
    Content-Type: application/json
    Transfer-Encoding: chunked
    Date: Sun, 17 Sep 2023 13:47:38 GMT
    Keep-Alive: timeout=60
    Connection: keep-alive
    
    {
        "isCorrect" : true
    }

### Error response

    HTTP/1.1 500 Internal Server Error
    Content-Type: text/plain;charset=UTF-8
    Content-Length: 38
    Date: Sun, 17 Sep 2023 11:16:26 GMT
    Connection: close
    
    Unknown error while processing request