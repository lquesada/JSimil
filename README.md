JSimil
======

Bytecode-level similarity detector for Java-written programs

JSimil is a Java-written programs similarities detection system that performs heuristic matching of programs, classes, methods and basic blocks at bytecode level. It allows the application of different working profiles with configurable behaviors and thresholds to batteries of programs and obtain several types of outcomes, including: clustering solutions, plagiarism detection and differences detection.


This multi-platform and multi-language system takes as input: a configuration, which indicates the path to the programs to use and aspects related with their use; and a battery of data, which contains the programs to compare.

As output, it can generate reports and summaries of results in plain text, xml, html format and diff format. The results are shown sorted by relevance, and the hierarchy of matches can be browsed through. The elements matches can be visually checked both at source code (if available) and at bytecode. Results can be integrated in any project in which a non purely textual comparison of Java programs has to be made. An example of use is as an external diff system for NetBeans or Eclipse IDEs.
