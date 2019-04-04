---
layout: project
type: project
image: ../images/ieee.png
title: RFC Downloader
permalink: projects/RFC
labels:
  - Golang
  - Web Scraping
  - Concurrency
summary: A commandline tool to view and download IEEE RFC's
---

I took a class on computer networking while in school, and I enjoyed the topics in the class.
I was however surprised by how nearly every networking protocol is either entirely codified by an RFC or at least laid out by one.

As a result while reading the textbook I found myself looking up RFC's frequently, so I made a tool to download all of them in Python.
This ended up not being terribly useful and it took about 8 hours because I wasn't downloading any of them at the same time.
But it was still nice to have.

So I set to work on a new version when I started to learn Go, as Go lends itself well to two things relevant to this project:

1. concurrent operations
1. command line tools

The first being the big advantage here, but the second one is nice too.
Go compiles to binaries so they're easy to call from the command line, and running `go install` will put the finished binary in your PATH, making it quite easy.

Go has a unique model of concurrency not shared with many other languages.
Go allows you to simply run functions in lightweight threads (goroutines) and then gives you the concurrency safe tools to interact with them.
Most useful of them are channels, which are essentially concurrency safe queues.
Channels allow you to give goroutines input or get output from a goroutine.
Channels are also blocking so they can be used to wait for goroutines to finish.
Go also supplies you with waitgroups which gives you the ability to wait for groups of goroutines, or you can just wait for a single goroutine to finish, more like traditional methods of multithreading.

I used channels to get the goroutines to give me errors and tell me when they finished, which made the proces pretty easy.
I had a couple of bigger issues with this.
The first was that I did something like this:

```go
for i := 0; i < 10; i++ {
    go func() {
        fmt.Println(i)
    }()
}
```

This seems okay, but isn't actually okay whatsoever.
This took me a while to debug and I didn't feel smart afterwards.
The issue here is that when you `go func` something the code isn't necessarily going to run now.
And the i iterator is still going to change, meaning that you probably won't get what you want.
We can test this out. Here's what we get:

```go
3
3
10
10
10
10
10
10
10
```

Not exactly what we were looking for!

Another issue I ran into wasn't a concurrency issue but was an issue nonetheless.
When a goroutine ran into an error (because it couldn't find the RFC it was trying to download) it would cause the program to run forever.
Odd.
This wasn't too big of an issue though, It would still run correctly and download everything.
After a while though I found the solution.
I had a channel of size n so if I had n things in the channel and tried to insert something, it would block.
If nothing bad happened, I would pass back a `nil` to the channel.
If an error occurred I would pass the error to the channel.
However, if an error occurred I would actually do both.
So, the goroutines would wait to insert into the channel and never be able to because I was also waiting for all the goroutines to finish becore I started reading stuff from the channel, which in retrospect isn't the the best way I could have done that.

But all in all, I'm pretty happy with the finished product.

<a href="https://github.com/tylergdorn/RFC">You can find the project here</a>