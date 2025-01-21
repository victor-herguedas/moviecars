# MovieCards UI

Este repositorio contiene el código de la interfaz de usuario para la aplicación de películas **MovieCards**. El proyecto utiliza un flujo de integración continua que automatiza el despliegue en diferentes entornos al cumplir con las pruebas y análisis de calidad definidos.

## Proceso de Integración Continua

El flujo de integración continua está diseñado para garantizar la calidad del código y facilitar despliegues confiables. Los principales aspectos del proceso son:

1. **Commit en la rama `main`**:
    - Cada vez que se realiza un commit en la rama principal, se ejecutan todos los tests automatizados.
    - Si todos los tests pasan, el código es integrado automáticamente a Azure.

2. **Análisis de calidad con SonarQube**:
    - Antes de realizar cualquier despliegue, los workflows ejecutan un análisis con SonarQube para evaluar la calidad del código.

3. **Despliegue automático**:
    - Si el análisis de calidad es satisfactorio, el código se despliega automáticamente al entorno correspondiente.

## Entornos

El proyecto cuenta con dos entornos principales:

### Producción
- **URL**: [MovieCards UI Producción](https://moviecars-herguedas.azurewebsites.net/)
- **Descripción**: Este es el entorno de producción donde los usuarios finales interactúan con la aplicación.

### Stage
- **URL**: [MovieCards UI Stage](https://moviecards-pre-herguedas.azurewebsites.net/)
- **Descripción**: Entorno de pruebas utilizado para validar cambios antes de su despliegue a producción. Este entorno está dedicado exclusivamente a la interfaz de usuario y utiliza el mismo servicio backend que producción.

### Servicio Backend Compartido
Ambos entornos, producción y stage, interactúan con el mismo servicio backend:
- **URL del servicio**: [MovieCards API Service](https://moviecards-service-herguedas.azurewebsites.net/)
- **Repositorio del servicio**: [Repositorio del backend](https://github.com/victor-herguedas/moviecards-service)

## Workflows

El repositorio contiene un workflow de QA configurado para garantizar la calidad del código antes de su despliegue:

- **Workflow de QA**:
    - Ejecuta SonarQube como paso previo a cualquier despliegue.
    - Está configurado para ejecutarse en un runner local optimizado para una arquitectura ARM64 de Apple, como el chip M1.

## Cómo contribuir
Si deseas contribuir a este proyecto, asegúrate de:
1. Hacer un fork del repositorio.
2. Crear una rama desde `main` para trabajar en tu funcionalidad o corrección de errores.
3. Abrir un Pull Request asegurándote de que los tests y el análisis de calidad con SonarQube pasen correctamente.

---

Si tienes dudas o sugerencias, no dudes en abrir un issue en este repositorio.
