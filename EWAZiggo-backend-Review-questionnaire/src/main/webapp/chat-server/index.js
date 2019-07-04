let express = require('express');
let app = express();

let http = require('http');
let server = http.Server(app);

let socketIO = require('socket.io');
let io = socketIO(server);

const port = 3000;
var employees = [];
var next = 0;

io.on('connection', (socket) => {
    console.log('user connected');


    socket.on('who-is-on', () => {
        console.log('checking employees....');
        console.log(employees.length + " EMPLOYEES ONLINE");
        io.sockets.to(socket.id).emit('who-is-on', employees.length);
    });

    socket.on('check-in', (employee) => {
        employee.socketId = socket.id;
        console.log('checking in...' + employee);
        employees.push(employee);
        console.log('employees online: ' + employees.length);
    });
    socket.on('new-message', (message) => {
        io.in(message.room).emit('new-message', message);
    });

    socket.on('check-out', (chats) => {
        for (var i = 0; i < employees.length; ++i) {
            var e = employees[i];
            console.log(employees[i].employee + ' checked out');
            if (e.socketId === socket.id) {
                employees.splice(i, 1);
            }
        }
        for (var x = 0; x < chats.length; ++x) {
            console.log('ending chat:');
            console.log(chats[x].chat.id);
            socket.to(chats[x].chat.id).emit('checked-out');
        }
    });

    socket.on('request-re', (token) => {
        console.log("request from " + token.client + "has been accepted by " + token.employee);
        socket.join(token.chat.id);
        socket.to(token.chat.id).emit('request-re', token);
    });

    socket.on('match', (token) => {
        console.log('MATCHING...');
        if (next >= employees.length - 1) {
            next = 0;
        } else {
            next++;
        }
        let employee = employees[next];
        console.log('matched with ' + employees[next].employee);
        token.employee = employee.employee;
        socket.join(token.chat.id);

        io.sockets.to(employee.socketId).emit('chat-request', token);
    });

    socket.on('disconnect', function () {
        for (var i = 0; i < employees.length; ++i) {
            var e = employees[i];
            console.log(employees[i].employee + ' terminated the connection');
            if (e.socketId === socket.id) {
                employees.splice(i, 1);
            }
        }
    });
    socket.on('end-chat', (chatId) => {
        console.log("ending chat: " + chatId);
        socket.to(chatId).emit('end-chat');
    });

});

server.listen(port, () => {
    console.log(`started on port: ${port}`);
});
