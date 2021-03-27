# Targeted Cleaner

This project is a RAM management app by killing background processes.

# Feature

- Kill background processes
- Set timer of process killing execution
- Set applications which are not to be killed by this app.

# Architecture

![architecture](https://user-images.githubusercontent.com/31719872/112725305-d814c300-8f5a-11eb-85cc-3b19e9296dd6.png)

| Layer              | Description                                                                                                             |
| ------------------ | ----------------------------------------------------------------------------------------------------------------------- |
| Android Layer      | This layer contains the implements of android component (i.e. Activities, Services, BroadcastReceivers etc).            |
| Component Layer    | This layer contains the abstract classes of android components and its router class.                                    |
| View Layer         | This layer contains the view of the activities using compose.                                                           |
| ViewModel Layer    | This layer contains the ViewModel classes for the data binding for the view, and the EventHandler classes for the view. |
| Domain Layer       | This layer contains the Entity and Business logic for this app.                                                         |
| Repository Layer   | This layer contains the persistence logic.                                                                              |
