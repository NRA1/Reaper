# Reaper
Flowchart designer and interpreter.

## Motive
On our high school (and many others in my country) we are taught fundamentals of programmatic thinking using Raptor
flowchart interpreter. But Raptor is ugly, buggy, only supports Windows, it's interface is for the 90s, and it's not
well maintained anymore.
<br/>If not the schools, for which changing software on many computers would be very problematic, at least students
would like to move to something more modern.
<br/>But here lies the problem: There is no viable alternative, everything is either horribly outdated, never got to
a usable state or is in another way inappropriate for our use case.
<br/>This is the purpose of Reaper: Providing a modern and well maintained alternative to Raptor, so students at least
have a choice.

## Technologies
Reaper uses Java as it's main programming language and QtJambi, Qt framework bindings for Java, as it's GUI framework.
<br/>Flowchart interpretation is (not jet) implemented in Ruby to provide dynamic typing capabilities.