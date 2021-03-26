FROM node:15.12.0-alpine3.10
ENV app=vue-frontend
WORKDIR /$app
ENV PATH /$app/node_modules/.bin:$PATH
RUN npm install vue-cli -g
ADD $app/package.json /$app/package.json
RUN npm install
ADD $app /$app
RUN npm run build
FROM nginx:1.19.8
ENV app=vue-frontend
#RUN rm /etc/nginx.conf.d/default.conf
ADD nginx/nginx.conf /etc/nginx.conf.d
COPY --from=0 $app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]