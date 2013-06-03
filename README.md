JSimil
======

JSimil (Bytecode-level similarity detector for Java-written programs) - www.jsimil.com  
Copyright (c) 2007-2009, Luis Quesada - https://github.com/lquesada

JSimil Quick Guide:

Section 1: Tool description.

  JSimil is a Java-written programs similarities detection system that performs heuristic matching of programs, classes, methods and basic blocks at bytecode level. It allows the application of different working profiles with configurable behaviors and thresholds to batteries of programs and obtain several types of outcomes, including: clustering solutions, plagiarism detection and differences detection.
  
  This multi-platform and multi-language system takes as input: a configuration, which indicates the path to the programs to use and aspects related with their use; a profile, that specifies matching aspects, high and low level; and a battery of data, which contains the programs to compare.
  
  As output, it can generate reports and summaries of results in plain text, xml, html and diff format. The results are shown sorted by relevance, and the hierarchy of matches can be browsed through. The elements matches can be visually checked both as source code (if available) and as bytecode. Results can be integrated in any project in which a non purely textual comparison of Java programs has to be made. An example of use is as an external diff system for NetBeans or Eclipse IDEs

Section 2: Dependencies.

  It's neccesary to have an installed JDK (Java Development Kit) version 1.5 or higher to use the Java compiler "javac" and the class file disassembler for Java "javap" for obtaining the bytecodes in text format. The system supports older versions and will support future versions, despite the changes that may be made in the format of class files, as "javap” offers the disassembled code in its own backward compatible format.

Section 3: Configuration.

  The configuration contains the supported source code encodings (Encoding), the path to the Java compiler “javac” (CompilerPath) and the Java disassembler “javap” (DisassemblerPath), the Class Path (ClassPath) and several loading battery options as: whether to compile Java code or not (Compile), whether to consider classes that were already compiled or not (AcceptCompiled), and whether to discard programs with compiling errors (DiscardErrors). Furthermore, a runtime autoconfiguration may be asked for (Autoconfigure). 
  
  The tool recognizes routes to “javac” and “javap” of the Java interpreter that is running and can perform an autoconfiguration, so it is recommended to use the Java interpreter “java” contained in the distribution of the JDK you want to use.

Section 4: Profile.

  The profile specifies how to apply the matching: If you want to make a reflexive matching or a matching between different programs, search for similarities or differences, generate results for non-matches, allow many to many matches, match only elements with the same name, or try to match simple elements inside complex elements or viceversa.
  
  It also specifies target thresholds for the matchings, acceptance thresholds, exploration limits and maximum permissible error in matches.
  
  Finally, it also specifies the weights of the attributes of the basic blocks for obtaining the matching value. A weight can be given to the total number of instructions of a block, the fact the block is start or end of a method, and the number of instructions of a certain kind.

Section 5: Program battery.

  The program battery is loaded from a directory containing subdirectories with programs. For example, the directory “battery” that contains the subdirectories “program1”, “program2”, “programa”, “programb”, and all of these directories contains in itself or its subdirectories a set of java, class or jar files.
  
  Battery loading takes a long time: it's necessary to detect contained classes, compile them, disassemble them, read the bytecode files, and preprocess (extract attributes of basic blocks) them. However, the already loaded battery can be dumped to a file and loaded instantly when processing is needed.
  
  If a class of a given package appears repeated in different files or directories of a program, only the first ocurrence will be considered. Anonimous classes, different packets and any kind of code that can be compiled with the used JDK is supported. 
  
Section 6: Utilization.

  6.1. Profile manager.

  Profile manager is a graphic user interface application that allows the editing of processing profiles. It has integrated contextual help about any profile attributes.
	It can be manually launched:
  
    $ java -jar JSimilProfileEditor.jar
  
  Or it can be launched using given linux, mac or windows scripts:
  
    $ ./profiles.sh
  
    \> profiles.bat
  
  6.2. Command-Line Interface Application.
  
  6.2.1. Help.

  A complete supported arguments description will be shown if the tool is executed without arguments or with -h argument:

    JSimil 1.0.4_(9) 2007-2009 (http://www.jsimil.com).
    Bytecode-level similarity detector for Java-written programs.
    
    Author: Luis Quesada.
    
    JSimilCLI - JSimil Command Line Interface.
    
    Usage: java -Xms128M -Xmx1618M -jar JSimilCLI <arguments>
    
    Argument list:
     -h/--help                Shows this help.
    
     --langlist               Lists the available language codes.
     -l/--lang <code>         Switchs the language to the one specified.
    
     -v/--verbose             Verbose mode.
     --hide-errors            Hide compile errors.
     -z/--threads <num>       Number of threads to use in load and processing.
    
     -f/--force-overwrite     Force overwriting output files.
     -n/--dont-process        Don't process, just load and save.
     -w/--force-battery-load  Force loading of battery programs.
     -i/--output-profile      Write profile information on every output.
     -y/--mind-size           Mind programs sizes for results ordering.
     -u/--results-limit <n>   Export information only about the most relevant n results.
    
     --new-config-to <file>   Write a new configuration to the specified file.
     --dont-autoconfig        Don't autoconfigure if error.
     -a/--autoconfig          Autoconfigure.
     -c/--config-load <file>  Load configuration from the specified file.
     --config-save <file>     Save configuration to the specified file.
    
     --new-profile-to <file>  Write a new profile to the specified file.
     --def-profile-to <file>  Write default profile to the specified file.
     --def-rprofile-to <file> Write default reflexive profile to the specified file.
     -p/--profile-load <file> Load profile from the specified file.
     -d/--profile-def         Use default profile.
     --rprofile-def           Use default reflexive profile.
     --profile-save <file>    Save profile to the specified file.
    
     --new-battery-to <file>  Write a new battery to the specified file.
     -b/--battery-load <file> Load battery from the specified file.
     -t/--battery-path <path> Creates a battery contained in given path.
     --battery-save <file>    Save battery information to the specified file.
     -g/--battery-dump <file> Save complete battery to the specified file.
    
     -q/--enable-all          Enable all programs in the battery to be processed.
     --enable-only <list>     Enable only specified programs in the battery to be processed (comma
                                separated names).
     --disable-only <list>    Enable all programs in the battery but the specified ones to be
                                processed (comma separated names).
    
     -e/--export-html <dir>   Make an HTML result report and save it to the specified directory.
     --export-rhtml <dir>     Make an HTML result resume and save it to the specified directory.
     --export-rrhtml <dir>    Make a short HTML result resume and save it to the specified
                                directory.
     -s/--export-txt <file>   Make an TXT result report and save it to the specified file.
     -j/--export-resume <fil> Make an TXT result resume and save it to the specified file.
     -m/--export-rxml <file>  Make an XML result resume and save it to the specified file.
     -x/--export-xml <file>   Make an XML result report and save it to the specified file.
     -k/--export-diff <dir>   Make diff format results and save them to the specified directory.
     --diff-simil <real>      Use given minimum similarity as difference threshold in diff format
                                results (0.0-100.0).
    
     -o/--output-standard     Write complete results to the standard command line output.
     -r/--resume-standard     Write a results resume to the standard command line output.
    
  6.2.2. Configuration.
  
  To configure the tool, you should first try an autoconfiguration (-a):
  
    $ java -jar JSimilCLI.jar -a
  
  If message “Successful autoconfiguration” is shown, the tool is correctly configured and you can skip until 6.2.3.
  
  If message “Can't autoconfigure. Use a manual configuration.”, is shown, you may try to include the JDK bin directory path to the system path with maximum priority, or to autoconfigure (-a) using the JDK interpreter:
  
    $ /opt/jdk1.6.0_05/bin/java -jar JSimilCLI.jar -a
  
  If message “Successful autoconfiguration” is shown, future tool calls can be made prepending the JDK binary directory path, or configuration may be exported (--config-save PATH):
  
    $ /opt/jdk1.6.0_05/bin/java -jar JSimilCLI.jar -a --config-save conf
  
  Tool could then be launched with the recently exported configuration (-c PATH) instead of autoconfiguration (-a):
  
    $ java -jar JSimilCLI.jar -c conf ...
    
  If this doesn't work, you should check JDK is correctly installed in specified path.
  
  6.2.3. Battery loading.
  
  Battery can be loaded from a path to the battery directory (-t PATH) and exported to a file (-g PATH). This way subsequent processings will take less time. Verbose mode can be activated (-v), and take care of updating memory limits (-Xms y -Xmx).
  
    $ java -Xms128M -Xmx1618M -jar JSimilCLI.jar -a -t PATH -g BAT -v
    
  6.2.4. Processing and HTML output.
  
  Finally, you may autoconfigure if applicable (-a), load battery from file (-b PATH), ask profile information in output (-i), sort results by size and similarity (-y), export as HTML (-e PATH), overwrite output files (-f), show standard output summary (-r), and show only the twenty most relevant results (-u NUM), update memory limit (-Xms y -Xmx) and specify profile to use (-p PATH):
  
    $ java -Xms128M -Xmx1618M -jar JSimilCLI.jar -a -b BAT -p PRO -i -y -e RE -f -r -u 20
    
  6.2.5. Processing and diff output.
  
  Instead of processing to obtain HTML results, you can processing to obtain diff format results. You may autoconfigure if applicable (-a), load battery from file (-b PATH), ask profile information in output (-i), sort results by size and similarity (-y), export as diff (-k PATH), overwrite output files (-f), show standard output summary (-r), and show only the twenty most relevant results (-u NUM), update memory limit (-Xms y -Xmx), specify profile to use (-p PATH), and specify similarity threshold for considering as difference (--diff-simil THRESHOLD):
  
    $ java -Xms128M -Xmx1618M -jar JSimilCLI.jar -a -b BAT -p PRO -u 20 --diff-simil 100 -k RE -f -i -y -r

  6.2.6. Load and processing scripts.

  Linux, mac and windows scripts are included. These scripts use the configuration contained in “default.jcf” (that by defaults autoconfigures) and automatize all this tasks:
  
  Load battery (replace .xx with .sh or .bat, according to the operating system):
  
    $ ./load.sh SOURCE_DIR TARGET_BATTERY_FILE
    > load.bat SOURCE_DIR TARGET_BATTERY_FILE
           
  Process and obtain HTML:
  
	$ ./process.sh BATTERY PROFILE NUM_RESULTS_TO_SHOW OUTPUT_DIR
	> process.bat BATTERY PROFILE NUM_RESULTS_TO_SHOW OUTPUT_DIR
    
  Process and obtain diff:
  
    $ ./diffs.sh BATTERY PROFILE NUM_RESULTS_TO_SHOW OUTPUT_DIR DIFF_THOLD
    > diffs.bat BATTERY PROFILE NUM_RESULTS_TO_SHOW OUTPUT_DIR DIFF_THOLD

  Process and obtain diff between two given files:
  
    $ ./diff.sh PATH_1 PATH_2 PROFILE DIFF_THOLD TEMP_DIR
    > diff.bat PATH_1 PATH_2 PROFILE DIFF_THOLD TEMP_DIR
    
  All those scripts show the argument help if launched without arguments.

  6.3. Integrating JSimil diff output on NetBeans.

  Script netbeansdiff.sh for Linux and Mac (and Windows if using unix-tools) is given. It serves as a diff program for NetBeans.

  The procedure for configuring NetBeans to use it is as follows:

  Go to “Options” at menu “Tools”.

  Inside “Miscellaneous” Tab look for “Diff”.

  Check “External diff”.

  Write command as follows:
  
	  /PATH/TO/netbeansdiff.sh -- {0} {1} profiles/diff.jpf 100 /tmp/jsimil

  Note that parameters are the same as diff.sh script. The 100 value is the similarity threshold and can be changed.

  As limitation, only files without dependence files can be compared, as NetBeans limit diff comparison to just two files, and it's neccesary to compile them (thus using their dependencies) to compare them using JSimil.

  You can modify profile path as you wish, but the working path of this script will be where it is placed.
