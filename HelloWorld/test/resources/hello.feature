Feature: Greeting

Scenario: Say hello world
	Given I have a greeting app with "Hello"
	When I ask it to say hi
	Then I receive "Hello World"