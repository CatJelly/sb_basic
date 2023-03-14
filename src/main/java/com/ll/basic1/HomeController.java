package com.ll.basic1;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class HomeController {
    private int count;
    private List<Person> list = null;

    public HomeController() {
        count = 0;
        list = new ArrayList<>();
    }

    @GetMapping("/home/main")
    @ResponseBody
    public String showMain() {
        return "안녕하세요.";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String showMain2() {
        return "반갑습니다.";
    }

    @GetMapping("/home/main3")
    @ResponseBody
    public String showMain3() {
        return "즐거웠습니다.";
    }

    @GetMapping("/home/increase")
    @ResponseBody
    public int showIncrease() {
        return count++;
    }

    @GetMapping("/home/plus")
    @ResponseBody
    public int showPlus(@RequestParam(defaultValue = "0") int a, int b) {
        return a + b;
    }

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(String name, int age) {
        list.add(new Person(++count, name, age));
        return String.format("%d번 사람이 추가되었습니다.", list.size());
    }

    @GetMapping("/home/removePerson")
    @ResponseBody
    public String removePerson(int id) {
        boolean remove = list.removeIf(people -> people.getId() == id);
        if (remove)
            return String.format("%d번 사람이 삭제되었습니다.", id);
        else
            return String.format("%d번 사람이 존재하지 않습니다.", id);
    }

    @GetMapping("/home/modifyPerson")
    @ResponseBody
    public String modifyPerson(int id, String name, int age) {
        Person found = list
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);

        if(found != null) {
            found.setId(id);
            found.setName(name);
            found.setAge(age);
            return String.format("%d번 사람이 수정되었습니다.", id);
        }
        else {
            return String.format("%d번 사람이 존재하지 않습니다.", id);
        }
    }

    @GetMapping("/home/people")
    @ResponseBody
    public List<Person> people() {
        return list;
    }
}

@AllArgsConstructor
@Getter
@Setter
class Person {
    int id;
    String name;
    int age;
}
