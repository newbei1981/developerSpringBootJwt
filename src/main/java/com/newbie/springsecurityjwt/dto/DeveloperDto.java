package com.newbie.springsecurityjwt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.newbie.springsecurityjwt.model.developer.Account;
import com.newbie.springsecurityjwt.model.developer.Developer;
import com.newbie.springsecurityjwt.model.developer.Skill;
import com.newbie.springsecurityjwt.service.DeveloperService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeveloperDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String specialty;

    private AccountDto accountDto;

    private Set<SkillDto> skillsDto;

    @Autowired
    @JsonIgnore
    private DeveloperService developerService;

    public Developer toDeveloper(){
        Developer developer = new Developer();
        developer.setId(id);
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setSpecialty(specialty);
        Account account = accountDto.toAccount();
        developer.setAccount(account);
        HashSet<Skill> tempSkills = new HashSet<>();
        Skill skill = new Skill();
        for(SkillDto skillDto:skillsDto){
            skill.setId(skillDto.getId());
            skill.setName(skillDto.getName());
            tempSkills.add(skill);
        }
        developer.setSkills(tempSkills);
        return developer;
    }

    public static DeveloperDto fromDeveloper(Developer developer){
        DeveloperDto developerDto = new DeveloperDto();
        developerDto.setId(developer.getId());
        developerDto.setFirstName(developer.getFirstName());
        developerDto.setLastName(developer.getLastName());
        developerDto.setSpecialty(developer.getSpecialty());
        developerDto.setAccountDto(AccountDto.fromAccount(developer.getAccount()));
        HashSet<SkillDto> tempSkillsDto = new HashSet<>();
        for(Skill skill:developer.getSkills()){
            tempSkillsDto.add(SkillDto.fromSkill(skill));
        }
        developerDto.setSkillsDto(tempSkillsDto);
        return developerDto;
    }

    public static List<DeveloperDto> developerDtoList (List<Developer> developers){
        List<DeveloperDto> developerDtoList = new ArrayList<>();
        for (Developer dev: developers){
            developerDtoList.add(fromDeveloper(dev));
        }
        return developerDtoList;
    }

}
