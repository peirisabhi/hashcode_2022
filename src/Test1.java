import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Intellij.
 * Author: abhis
 * Date: 25/02/2022
 * Time: 12:41 am
 */
public class Test1 {

    static List<Contributor> contributorList = new ArrayList<>();
    static List<Project> projectList = new ArrayList<>();
    static List<Project> nonProjectList = new ArrayList<>();

    public static void main(String[] args) {
        readFile("a_an_example.in.txt", "a_an_example.out.txt");
        readFile("b_better_start_small.in.txt", "b_better_start_small.out.txt");
        readFile("c_collaboration.in.txt", "c_collaboration.out.txt");
        readFile("d_dense_schedule.in.txt", "d_dense_schedule.out.txt");
        readFile("e_exceptional_skills.in.txt", "e_exceptional_skills.out.txt");
        readFile("f_find_great_mentors.in.txt", "f_find_great_mentors.out.txt");
    }


    public static void readFile(String inputFile, String outputFile) {

        List<String> lines = new ArrayList<>();

        try {
            FileReader fr = new FileReader("E:\\Abhishek\\hashcode\\hashcode_2022\\input\\" + inputFile);
            BufferedReader br = new BufferedReader(fr);

            StringBuffer sb = new StringBuffer();
            String line = null;

            int count = 0;

            while ((line = br.readLine()) != null) {
//            for (int i = 0; i < contributorCount; i++) {
                sb.append(line);


                lines.add(line);
//                String[] split = line.split(" ");

//                count++;
            }

//            br.lines().collect(Collectors.toList());
            int projectCount = 0;
            int contributorCount = 0;
            int contributorCountVal = 0;

            String contributor = null;

            for (int i = 0; i < lines.size(); i++) {

                if (i == 0) {
                    contributorCount = Integer.valueOf(lines.get(i).split(" ")[0]);
                    projectCount = Integer.valueOf(lines.get(i).split(" ")[1]);
                } else {

                    if (contributorCount != contributorCountVal) {
                        String[] row = lines.get(i).split(" ");
                        contributor = row[0];
                        int skillCount = Integer.parseInt(row[1]);
//                        System.out.println(contributor);

                        List<Skill> contributorSkills = new ArrayList<>();

                        for (int sc = (i + 1); sc <= (i + skillCount); sc++) {
//                            System.out.println(lines.get(sc));
                            contributorSkills.add(new Skill(lines.get(sc).split(" ")[0], Integer.parseInt(lines.get(sc).split(" ")[1])));
                        }
                        contributorList.add(new Contributor(contributor, Integer.parseInt(row[1]), contributorSkills));

                        i += skillCount;
                        contributorCountVal++;
                    } else {
                        String[] row = lines.get(i).split(" ");

                        List<Skill> projectSkills = new ArrayList<>();
                        int skillCount = Integer.parseInt(row[4]);

                        if (row.length > 2) {
//                            System.out.println(lines.get(i));

                            for (int sc = (i + 1); sc <= (i + skillCount); sc++) {
//                                System.out.println(lines.get(sc));
                                projectSkills.add(new Skill(lines.get(sc).split(" ")[0], Integer.parseInt(lines.get(sc).split(" ")[1])));
                            }
                            projectList.add(new Project(row[0], skillCount, projectSkills));

                            i += skillCount;

                        }


                    }


                }

            }

            System.out.println("projectCount --- " + projectCount);
            System.out.println("contributorCount --- " + contributorCount);

//            for (Contributor contributor1 : contributorList){
//                System.out.println(contributor1.toString());
//                System.out.println(contributor1.getSkills().toString());
//            }
            for (Project project : projectList) {
//                System.out.println(project.name);
//                System.out.println(project.getSkills().toString());

                for (Skill projectSkill : project.getSkills()) {
//                    System.out.println("required skills - " + projectSkill.skill);

                    for (Contributor contributor1 : contributorList) {
//                        System.out.println(contributor1.name);
                        for (Skill contributorSkill : contributor1.getSkills()) {
//                            System.out.println(contributorSkill.skill);
                            if (projectSkill.skill.equals(contributorSkill.skill)) {

                                if (projectSkill.level <= contributorSkill.level) {
//                                    System.out.println(project.name);
//                                    System.out.println(contributor1.name + " | " + contributorSkill.level);
                                    if (projectSkill.level >= contributorSkill.level) {
                                        contributorSkill.level++;
                                    }
                                    if(project.contributionCount != project.contributorList.size()) {
                                        if (!project.contributorList.contains(contributor1)) {
                                            project.contributorList.add(contributor1);
                                        }
                                    }
                                } else {

                                }

                            }

                        }

                    }

                }

                if (project.contributionCount != project.contributorList.size()) {
                    nonProjectList.add(project);
                }

            }

            System.out.println("nonProjectList.size()--- " + nonProjectList.size());
            for (Project project : nonProjectList) {
//                System.out.println(project.name);
//                System.out.println(project.getSkills().toString());

                for (Skill projectSkill : project.getSkills()) {
//                    System.out.println("required skills - " + projectSkill.skill + " " + projectSkill.level);

                    for (Contributor contributor1 : contributorList) {
//                        System.out.println(contributor1.name);
                        for (Skill contributorSkill : contributor1.getSkills()) {
//                            System.out.println(contributorSkill.skill + " " +contributorSkill.level );
                            if (projectSkill.skill.equals(contributorSkill.skill)) {

                                if (projectSkill.level <= contributorSkill.level) {
//                                    System.out.println(project.name);
//                                    System.out.println(contributor1.name + " | " + contributorSkill.level);
                                    if (projectSkill.level >= contributorSkill.level) {
                                        contributorSkill.level++;
                                    }
                                    if(project.contributionCount != project.contributorList.size()) {
                                        if (!project.contributorList.contains(contributor1)) {
                                            project.contributorList.add(contributor1);
                                        }
                                    }
                                }

                            }

                        }

                    }

                }


            }


//            for (Project project : projectList){
//                System.out.println(project.toString());
//            }

            writeFile(projectList, outputFile);


        } catch (Exception e) {
            e.printStackTrace();
        }

        projectList.clear();
        nonProjectList.clear();
        contributorList.clear();

    }


    public static void writeFile(List<Project> projectList, String outputFile) {
        try {
            File myObj = new File("E:\\Abhishek\\hashcode\\hashcode_2022\\output\\" + outputFile);
            if (myObj.createNewFile()) {
                FileWriter myWriter = new FileWriter(myObj);

                myWriter.write(projectList.size() + " \n");
//                for (String s : projectList) {
//                    myWriter.write(s + " ");
//                }

                for (Project project : projectList) {
                    String contributors = "";
                    for (Contributor contributor : project.contributorList) {
                        contributors += contributor.name + " ";
                    }
                    myWriter.write(project.name + " \n");
                    myWriter.write(contributors + " \n");

                }

                myWriter.close();

                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    static class Contributor {
        String name;
        int skillCount;
        List<Skill> skills;

        @Override
        public String toString() {
            return "Contributor{" +
                    "name='" + name + '\'' +
                    ", skillCount=" + skillCount +
                    ", skills=" + skills +
                    '}';
        }

        public Contributor(String name, int skillCount) {
            this.name = name;
            this.skillCount = skillCount;
        }

        public Contributor(String name, int skillCount, List<Skill> skills) {
            this.name = name;
            this.skillCount = skillCount;
            this.skills = skills;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSkillCount() {
            return skillCount;
        }

        public void setSkillCount(int skillCount) {
            this.skillCount = skillCount;
        }

        public List<Skill> getSkills() {
            return skills;
        }

        public void setSkills(List<Skill> skills) {
            this.skills = skills;
        }
    }

    static class Skill {
        String skill;
        int level;

        @Override
        public String toString() {
            return "Skill{" +
                    "skill='" + skill + '\'' +
                    ", level=" + level +
                    '}';
        }

        public Skill(String skill, int level) {
            this.skill = skill;
            this.level = level;
        }
    }


    static class Project {
        String name;
        int contributionCount;
        List<Skill> skills;
        List<Contributor> contributorList = new ArrayList<>();

        public List<Contributor> getContributorList() {
            return contributorList;
        }

        public void setContributorList(List<Contributor> contributorList) {
            this.contributorList = contributorList;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getContributionCount() {
            return contributionCount;
        }

        public void setContributionCount(int contributionCount) {
            this.contributionCount = contributionCount;
        }

        public List<Skill> getSkills() {
            return skills;
        }

        public void setSkills(List<Skill> skills) {
            this.skills = skills;
        }

        public Project(String name, int contributionCount, List<Skill> skills) {
            this.name = name;
            this.contributionCount = contributionCount;
            this.skills = skills;
        }


        @Override
        public String toString() {
            return "Project{" +
                    "name='" + name + '\'' +
                    ", contributionCount=" + contributionCount +
                    ", skills=" + skills +
                    ", contributorList=" + contributorList +
                    '}';
        }
    }

}
