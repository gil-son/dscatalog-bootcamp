# DSCatalog-bootcamp-devsuperior

## Introduction

<p> DSCatalog is a Bootcamp promoted by <a href="https://devsuperior.com.br/"> DevSuperior <a/> which aims to
help students and programming professionals to enter or re-enter their careers.</p>

<p>That event improve abilities required to an Full Stack Developer build a system with a quality architecture!</p>

## Event

<p>The estimate is up to 12 weeks to terminate. But if necessary, you can increase the deadline. Some stages:</p>

<ul>
	<li>* [x] Introducion</li>
	<li>* [ ] CRUD</li>
	<li>* [ ] Automated testing</li>
</ul>



## Technical knowledge

<ul>
	<li><a href="https://youtu.be/xC_yKw3MYX4">OO and SQL</a></li>
	<li><a href="https://youtu.be/os6hdZbCnpM">Jpa Repository</a></li>
	<li><a href="https://youtu.be/OX5MmJrFTdw">Domain Model</a></li>
	<li><a href="https://youtu.be/IOJoJGDowEY">React JS</a></li>
	<li><a href="https://youtu.be/KLG-jC1fh28">Git and Github</a></li>
  <li><a href="https://www.youtube.com/watch?v=2mmKjJb9EBE">CSS Responsive</a></li>
</ul>

## Tools list

<ul>
	<li>Backend - Optional IDEs to Java 
		<ul>
			<li>Spring Tool Suite (STS)</li>
			<li>Eclipse</li>
			<li>intellij</li>
		</ul>	
	</li>
	<li>Backend - Optional IDEs to SQL 
		<ul>
		    <li>pgAdmin - PostgreSQL</li>
		</ul>
	</li>
	<li>Frontend - Optional IDEs to React JS 
		<ul>
			<li>VS Studio Code</li>
			<li>Atom</li>
		</ul>	
	</li>
	<li>RESTful services
		<ul>
			<li>Postman</li>
			<li>Insomnia</li>
		</ul>
	</li>
</ul>




# Layers Pattern

<a href="https://imgbox.com/PAUEeYER" target="_blank"><img src="https://images2.imgbox.com/45/77/PAUEeYER_o.png" alt="image host"/></a>




<p>
Front end: application that the client uses on his device, that is,
is accessible on the client side.
</p>
<p>
Back end: is the part of the system that is running on the server.


<p>
The user using the Front end is exchanging interactions and / or
data with Back end. This data exchange can be done in several ways
forms, but in this case, in the context of the web, requests are being used
<a href="https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol"> HTTP <a/> (Hypertext Transfer Protocol)
</p>

<p>
Analyzing the internal part of the Back end we have logical layers, each partition
has a purpose and is divided into 3 main layers:
</p>



<ul>
	<li> Rest Controllers
		<ul>
			<li> You will receive requests from the Front end to the Back end </li>
			<li> Forwards the customer's actions to the Service Layer </li>
		</ul>
	</li>
	<li> Service Layer
		<ul>
			<li> Has the system / business logic </li>
			<li> Responsible for checks, calculations, orchestration of various operations </li>
		</ul>
	</li>
	<li> Data Access Layer
		<ul>
			<li> Basic data access operations in the database (CRUD) </li>
		</ul>
	</li>
		</ul>

<p>
This system organization is important to make the system flexible and easy to maintain.
</p>


<p>
Analogy to show the difference between the Service Layer and Data Access Layer layers:

</p>

<p>
Imagine that you will need to save an order on an e-commerce system. To save the
order it is necessary to trigger / call a function from the database. However, before saving this
order it is necessary to do a lot of checks, in which the Service Layer layer will be
responsible:
</p>

<ul>
  <li> Check if the stock is up to date </li>
  <li> Write off inventory </li>
<li> If the inventory policy allows you to sell without having the product </li>
<li> Write off inventory </li>
<li> Send e-mail to the customer, notifying the customer about the order </li>
</ul>

<p>
The operations that go directly into the database and do something, is that
will be in the Data Access Layer (CRUD) layer. But the whole orchestration, the order
when things have to happen, it will be on Service Layer.
</p>

<p>
Data Transfer Objects (DTO): are simple objects to load / traffic data between Rest Controllers
and Service Layer. But when you enter the Service Layer and need to make transactions with the data
then Entities will be instated. The concept of Entities in this scenario, is that Entities will play the role
of objects that will be monitored, controlled ... to maintain the integrity of the database.
So the moment you access the Service Layer and the orchestrations / operations start to occur ...
Entities will be instantiated, saved, deleted, updated, etc ... Using the
Data Access Layer ... but all of these operations are being monitored to maintain integrity
of the data.

</p>



## Source

<ul>
  <li><a href="https://devsuperior.com.br/">DevSuperior</a></li>
</ul>

