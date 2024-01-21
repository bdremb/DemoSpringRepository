FROM openjdk:17-oracle

WORKDIR /app

COPY build/libs/demo-socketclient-0.0.1-SNAPSHOT.jar app.jar

# Определим переменные среды
ENV SOCKET_SERVER_HOST=localhost
ENV SOCKET_SERVER_PORT=8888

CMD ["java", "-jar", "app.jar"]

# выполнить в терминале
# docker build -t socket-client .
#
# запуск контейнера в докере
# docker run socket-client
#
# запуск контейнера на нужном порту, а именно 8888
# -p привязывает порты между хостом и контейнером, справа порт контейнера
# docker run -p 8888:8888 socket-client
#
# запуск сервера а потом клиента, --rm для удаления контейнера после использования
# docker run -p 8888:8888 socket-server
# docker run --rm socket-client
#
# docker container ls
# docker image ls
# docker image rm    удаление образов
# docker image  rm socket-client -f
# docker container ls -a    просмотреть в том числе не запущенные образы
# docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' f52f088ce042
#
# docker run --rm  socket-server
#
# docker run --rm -e SOCKET_SERVER_HOST=172.17.0.2 socket-client
#
#
#
# docker network create spring-socket-network
#
# docker run -p 8888:8888 --rm --name socket-server --network spring-socket-network socket-server
# docker run --rm --name socket-client --network spring-socket-network -e SOCKET_SERVER_HOST=socket-server socket-client
#