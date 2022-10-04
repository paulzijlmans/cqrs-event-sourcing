## Microservices

**Microservices**:  
Small, loosely coupled applications or services that can fail
independently of each other. 

When a microservice fails, only a single function or process in the system should
become unavailable, while the rest of the system remains unaffected.

**Principles of Microservices**
* Microservices should not share code or data.
* Avoid unnecessary coupling between services and software components.
* Independence and autonomy are more important than code re-usability.
* Each microservice should be responsible for a single system function or process.
* Microservices should not communicate directly with each other, they should make use of
an event/message bus to communicate with one another.

## CQRS and Event Sourcing

CQRS: Command Query Responsibility Segregation
* Commands alter the state of an object.
* Queries return the state of an object.

Event Sourcing defines an approach where all the changes that are made to an object or
entity, are stored as a sequence of immutable events to an event store, as opposed to
storing just the current state.

## Axon

Axon is a platform that consists of the Axon Framework and the Axon Server.

The Axon Framework is a Java framework that is used to simplify the building of event-driven
microservices that are based on CQRS, Event-Sourcing and Domain-Driven Design.

Axon Server is an "out of the box" message router and event store that requires no specific
configuration.

### Domain Model
A Domain Model describes certain aspects of a system domain that can be used to solve problems
within that domain.

### Aggregate
An Aggregate is an entity or group of entities that is always kept in a consistent state
(within a single ACID transaction).  
The Aggregate Root is the entity within the aggregate that is responsible form maintaining 
this consistent state.  
This makes the aggregate the prime building block for implementing a command model in any
CQRS based application.

ACID:  
Atomicity, Consistency, Isolation, Durability

### Commands
A Command is a combination of expressed intent (which describes what you want to be done) as well
as the information required to undertake action based on that intent.

Command are named with a verb in the imperative mood, for example RegisterUserCommand or
DepositFundsCommand.

### Events
Events are objects that describe something that has occurred in the application. A typical source of
events is the aggregate. When something important has occurred within the aggregate, it will raise
an event.

Events are always named with past-participle verb, for example UserRegisteredEvent or
FundsDepositedEvent.

### Queries
Queries express the desire for information, generally a specific representation of the state of
the system.