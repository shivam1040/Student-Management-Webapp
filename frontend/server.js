const jsonServer = require('json-server');
const server = jsonServer.create();
const router = jsonServer.router('./database/user.json');
const middlewares = jsonServer.defaults({
    static: './dist'
});
const port = process.env.PORT || 8000;

server.use(middlewares);

  server.use(router);
server.listen(port, () => {
    console.log("Server running")
});