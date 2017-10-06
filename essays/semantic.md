---
layout: essay
type: essay
title: Semantic UI
date: 2017-10-05
labels:
  - Semantic UI
  - Separation
  - Good Practice
---
 When we program we should try to separate all aspects of the program. It makes everything more readable, decreases potential error sites, and increases code clarity. As a result, web programming platforms have been made in a way to separate content, function, and style via different languages. We use HTML to only describe content, CSS to only describe style, and JavaScript to describe how a page functions. 
While this isn’t always the case, when good code practices are followed, this is generally how it goes. In the old days of html, all style was done in the html, via specific styling tags, tables, and other now unused features. We moved away from this model to a new model where we separate all style into a CSS file, and this has generally increased readability of html files. 


However, putting styling into html has reared its ugly head again. With Semantic UI, you put nearly all style into the class of a specific html element, or by surrounding it with more html elements with more things in the class. This turns classes into a bastardization of what they should be, a way to separate elements in terms of similar style, not to style the element specifically. This adds to the difficulty in styling elements with CSS, as you now require some extremely long CSS selector with all the classes of a specific element, and there are often as many as five or six. When you try to figure out why an element doesn’t look how you want it to, there is no clear answer, you can’t see how semantic is processing elements or how it is building up the CSS.


When working in pure CSS you can see clearly where the individual rules are coming from, their hierarchy, and how they affect each other. In Semantic, you have about a hundred rules, which you have no idea where they’re coming from or what they’re doing. As a result, it is extremely frustrating to figure out how to fix something in Semantic UI, where I could easily fix it in pure CSS.


While Semantic UI does enable the developer to create nice looking albeit perhaps unoriginal websites quickly, I wouldn’t want to use it for something that would take a lot of effort, as the frustration of making a site in Semantic outweighs the speed of getting it to look right. 
<img class="ui image" src="https://www.smashingmagazine.com/wp-content/uploads/2013/10/class_stop_mini.jpg">

