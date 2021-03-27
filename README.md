# Targeted Cleaner

This project is a RAM management app by killing background processes.

# Feature

- Kill background processes
- Set timer of process killing execution
- Set applications which are not to be killed by this app.

# Architecture

<p float="center">
  <img src="https://user-images.githubusercontent.com/31719872/112725305-d814c300-8f5a-11eb-85cc-3b19e9296dd6.png" width="600" />
</p>

| Layer              | Description                                                                                                             |
| ------------------ | ----------------------------------------------------------------------------------------------------------------------- |
| Android Layer      | This layer contains the implements of android component (i.e. Activities, Services, BroadcastReceivers etc).            |
| Component Layer    | This layer contains the abstract classes of android components and its router class.                                    |
| View Layer         | This layer contains the view of the activities using compose.                                                           |
| ViewModel Layer    | This layer contains the ViewModel classes for the data binding for the view, and the EventHandler classes for the view. |
| Domain Layer       | This layer contains the Entity and Business logic for this app.                                                         |
| Repository Layer   | This layer contains the persistence logic.                                                                              |

```
Copyright 2021 Lee PyeongWon

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
