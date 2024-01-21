# Базовый образ, содержащий java 17
FROM openjdk:17-oracle

#Директория проложения внутри контейнера
WORKDIR /app

# Копирование jar файла приложения в контейнер
COPY build/libs/demo-socketserver-0.0.1-SNAPSHOT.jar app.jar

# Команда для запуска приложения
CMD ["java", "-jar", "app.jar"]

# выполнить в терминале
# docker build -t socket-server .
#
# запуск контейнера в докере
# docker run socket-server
#
# запуск контейнера на нужном порту, а именно 8888
# -p привязывает порты между хостом и контейнером, справа порт контейнера
# docker run -p 8888:8888 socket-server
#
#