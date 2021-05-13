FROM node:12-alpine
WORKDIR /nodejs
COPY package.json /nodejs
RUN npm install
COPY . /nodejs
CMD ["npm", "start"]
EXPOSE 5000
