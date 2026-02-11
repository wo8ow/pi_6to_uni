# Etapa 1: Construcción
FROM node:20-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build -- --configuration production

# Etapa 2: Servidor Nginx
FROM nginx:alpine
COPY --from=build /app/dist/smartfin-web/browser /usr/share/nginx/html
# Copiamos la config personalizada para manejar el enrutamiento de Angular
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80