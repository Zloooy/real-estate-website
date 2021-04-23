FROM node:15.12.0-alpine3.10
ENV APP="vue-frontend"
WORKDIR /$APP
RUN npm install @vue/cli -g
ADD $APP/package.json /$APP/package.json