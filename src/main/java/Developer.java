import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Developer {
    private String name;
    private List<String> languages;

    public Developer(String name, List<String> languages) {
        this.name = name;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public static void main(String[] args) {

        Developer dev1 = new Developer("Наташа", Arrays.asList("Java", "C++"));
        Developer dev2 = new Developer("Эрнест", Arrays.asList("Java", "Python"));
        Developer dev3 = new Developer("Элла", Arrays.asList("С#", "Python", "JavaScript"));
        Stream<Developer> developerStream = Stream.of(dev1, dev2, dev3);
        List<Developer> developers = developerStream.flatMap(dev -> dev.getLanguages().stream().map(lang -> new AbstractMap.SimpleEntry<>(lang,dev)))
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())))
                .values().stream()
                .filter(dev -> dev.size() == 1)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        developers.forEach(dev -> System.out.println(dev.getName()));
    }
}
