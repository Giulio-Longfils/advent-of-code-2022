package day7


import java.util.regex.Matcher

class Day7_2 {

    public static final int THRESHOLD = 100000
    public static final int AVAILABLE = 70000000
    public static final int UNUSED_TARGET = 30000000

    static void main(String[] args) {
        //process('src/main/resources/day7/example.txt')
        process('src/main/resources/day7/real.txt')
    }

    static void process(String name) {
        List lines = new File(name).readLines()
        Folder root = null
        Folder current

        lines.each { String line ->
            switch (line) {
                case ~/^\$\scd\s\//:    // root
                    root = new Folder(name: '/')
                    current = root
                    break
                case ~/^\$\scd\s\.{2}$/:  // cd ..
                    current = current.parent
                    break
                case ~/^\$\scd\s(?<dest>.*$)/:  // cd subFolder
                    Matcher matcher = line =~ ~/^\$\scd\s(?<dest>.*$)/
                    matcher.matches()
                    String dest = matcher.group('dest')
                    Folder subFolder = new Folder(name: dest, parent: current)
                    current.subFolders << [(dest): subFolder]
                    current = subFolder
                    break
                case ~/^\$\sls(.*$)/:   // ls: skip
                    break
                case ~/^dir(.*$)/: // dir: skip
                    break
                case ~/^(?<size>[0-9]+)\s(?<file>[A-Za-z]+\.[A-Za-z]+)$/: // file with size
                    Matcher matcher = line =~ ~/^(?<size>[0-9]+)\s(?<file>[A-Za-z]+\.[A-Za-z]+)$/
                    matcher.matches()
                    int size = matcher.group('size') as int
                    String file = matcher.group('file')
                    println("file: ${file} size: ${size}")
                    current.size += size
                    break
                default:    // folders with size
                    Matcher matcher = line =~ ~/^(?<size>[0-9]*)\s(?<file>.*)$/
                    matcher.matches()
                    int size = matcher.group('size') as int
                    String file = matcher.group('file')
                    println("folder: ${file} size: ${size}")
                    current.size += size
                    current.subFolders << [(file): new Folder(name: line, parent: current)]
                    break
            }
        }

        println("\n\nSIZE")
        int total = getSize root
        int unused = AVAILABLE - total
        int toBeFreed = UNUSED_TARGET - unused


        println("\n\nTOTAL: ${total}")
        println("UNUSED: ${unused}")
        println("TO BE FREED: ${toBeFreed}\n\n")

        Folder smallest = findToDelete(root, toBeFreed)
        println("\n\nSMALLEST: ${smallest.name} ${smallest.size}")
    }

    static int getSize(Folder f) {
        int accumulator = f.size
        f.subFolders.each { String key, Folder subFolder ->
            accumulator += getSize subFolder
        }

        f.size = accumulator
        println("FOLDER ${f.name} -> ${f.size}")
        accumulator
    }

    static Folder findToDelete(Folder f, int toBeFreed) {
        Folder smallest = f
        f.subFolders.each { String key, Folder subFolder ->
            Folder smallestSubFolder = findToDelete subFolder, toBeFreed

            if (smallestSubFolder.size > toBeFreed && smallestSubFolder.size < smallest.size) {
                smallest = smallestSubFolder
            }
        }

        smallest
    }
}
