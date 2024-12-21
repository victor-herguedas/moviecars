# Usar la imagen base de SonarQube
FROM sonarqube:lts-community

# Asegurarse de ejecutar como root
USER root

# Exponer el puerto 9000 para SonarQube
EXPOSE 9000

# Actualizar los paquetes e instalar herramientas necesarias
RUN apt-get -y update && \
    apt-get install -y curl && \
    apt-get install -y maven

# Crear y moverse al directorio del runner
RUN mkdir /actions-runner && cd /actions-runner
WORKDIR /actions-runner

# Permitir ejecutar como root
ENV RUNNER_ALLOW_RUNASROOT=1

# Descargar y descomprimir el runner para Linux arm64
RUN curl -o actions-runner-linux-arm64-2.321.0.tar.gz -L https://github.com/actions/runner/releases/download/v2.321.0/actions-runner-linux-arm64-2.321.0.tar.gz && \
    tar xzf ./actions-runner-linux-arm64-2.321.0.tar.gz

# Configurar el runner
RUN ./config.sh --url https://github.com/victor-herguedas/moviecars --token AKLO3IMDC7PKURS46U6EORDHM26OK

# Ejecutar el runner
CMD ["./run.sh"]