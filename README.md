# CEBPproj2
Sistem de stiri

Aplicatia implementeaza un sistem de stiri orientat pe evenimente. Un
eveniment este aparitia, modificarea sau stergerea unei stiri, iar
stirile sunt organizate in domenii si un numar arbitrar de subdomenii.
Stirile au si alte atribute cum ar fi: data primei publicari, data
ultimei modificari, sursa de informatie, autorul articolului etc.

Actorii din sistem sunt de doua tipuri: editori de stiri si cititori.
Editorii trebuie sa poata afla in timp real care este numarul de
cititori pentru stirile de interes. Pentru aceasta, ei se pot declara
interesati de aparitia unui eveniment gen "stire citita". Cititorii se
pot abona la una sau mai multe stiri, specificand domeniile de interes
si alte atribute (data, sursa etc.).

Project Type B:

The architecture is similar to Project Type A, with the exception that the dispatcher will not be implemented in the application. Instead, the students must use an existing software infrastructure (service) that provide dispatching functionality. The technology of choice in this respect is Java Message Service (JMS), by using Topic resources to model the dispatcher, with events being represented by asynchronous JMS messages.

As above, events have types represented by a character string ID, and the event consumers register to the JMS server by specifying the types of events they are interested in. A consumer can register to more than one event types.

For both types of projects, event consumers are specific classes that receive the events sent by the dispatcher, and take actions according to the project specification. 
