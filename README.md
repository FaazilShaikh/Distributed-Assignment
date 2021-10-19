## About

This program is a client/server application that has four commands:

* rps (selection) - valid selections: rock, paper, scissors
* mq
* sphereVolume (radius)
* conch (yes/no question)

-rps stands for rock paper scissors, this allows you to play rock paper scissors with the server. For example if you choose rock you would type in rps rock and enter, the server will randomly select their own selection, compute the outcome and send back the results 

-mq stands for multiplication quiz which will allow you to do a 5 question multiplication quiz where the server will send you a question and ask you to send an answer, once finishing the 5 questions the server will send back how many you've gotten correct

-sphereVolume command lets you compute the volume of a sphere given the radius

-conch command lets you ask the server a question and it’ll give an answer back on what it thinks

## How to run

**Ensure you run server before opening client**

This program doesn't need any command line arguments so you can just run as is, class files are in out/production/Assignment1/

If you'd like you can go ahead and edit the port/host inside the .java files in the src folder, which then you would compile using javac 

I would personally reccomend just opening with any IDE like intellij/eclipse which you can just create a new project and run using that IDE so it automatically compiles it for you, if you do decide to go this route you may have to enable multiple instances within the IDE settings to test for the multi-threaded server capabilities!
