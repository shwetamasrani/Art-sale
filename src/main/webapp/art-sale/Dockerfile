# Step 1
FROM node:10-alpine as build-step
RUN mkdir /app
WORKDIR /app
COPY src/main/webapp/art-sale/package*.json /app
RUN npm install
COPY src/main/webapp/art-sale /app
RUN npm run build
# Stage 2
FROM nginx:1.17.1-alpine
COPY --from=build-step /app/build /usr/share/nginx/html
EXPOSE 80

