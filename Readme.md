# Test API for frontend quizz

This is a simple scheduling backend, it is designed as a test for frontend developers at Healthcare.com, the idea is very simple, we have an API to create tasks and schedule execution of tasks, as well as delaying or stopping tasks, you have to create a simple frontend in your preferred Javascript UI framework (React, Vue, etc).

You can visit the application at this Heroku app [https://lit-oasis-69017.herokuapp.com/](https://lit-oasis-69017.herokuapp.com/).

There is a Swagger page for the API, you can check it out at [/swagger-ui.html/](https://lit-oasis-69017.herokuapp.com/).

The backend has CORS enabled, so your application should run locally at `http://localhost:4200`, feel free to use any local development webserver with that port.

The app currently uses a local SQLite database, so it is possible there are no tasks when you use it the first time.
