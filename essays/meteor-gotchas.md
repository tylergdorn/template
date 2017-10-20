---
layout: essay
type: essay
title: "Meteor Gotchas"
date: 2017-10-19
labels:
  - Software Engineering
  - Meteor
---

Meteor has been a struggle for many, including myself. While I enjoy the framework and it seems like a good way to make web apps quickly and have them look and respond well, for many windows users the truth of the matter is that meteor doesn't work well.
Meteor restarting itself whenever you change files, and refreshing the page is really a great boon to productivity, as it allows you to keep on coding and not have to think about restarting the framework whenever you want to test something.
My biggest issue so far has been an insane amount of time taken to get meteor to start. While this is a common issue amongst meteor users, for myself it takes forever, but only on my laptop. On my desktop it will start nearly instantly. It would tend to hang on "Extracting Meteor Tools".
I eventually found out that the issue was *probably* because I had the audacity to have 7-zip installed on my system, and uninstalling it fixed my issues. Mostly. There was some other odd issues still which I was able to fix by deleting some magic file which was breaking meteor for no apparent reason.
Additionally, in my struggle to get meteor working somewhere, I came to the realization that it doesn't seem to work on windows bash subsystem, which was fun.

Other issues I've had with meteor so far 

<img class="ui image" src="../images/meteor_irl.png">

