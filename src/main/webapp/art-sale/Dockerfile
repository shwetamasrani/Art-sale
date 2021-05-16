# Step 1
FROM node:12 as build-step
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
# Stage 2
CMD ["npm", "start"]

FROM nginx
EXPOSE 3000
COPY ./nginx-art.conf /etc/nginx/conf.d/default.conf
COPY --from=build-step /app/build /usr/share/nginx/html

