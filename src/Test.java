import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Intellij.
 * Author: abhis
 * Date: 24/02/2022
 * Time: 11:23 pm
 */
public class Test {

    static List<Contributor> contributorList = new ArrayList<>();

    public static void main(String[] args) {
        readFile("a_an_example.in.txt", "a_an_example.out.txt");
    }


    public static void readFile(String inputFile, String outputFile) {
//        HashSet<String> likes = new HashSet<>();
//        HashSet<String> disLikes = new HashSet<>();


        try {
            FileReader fr = new FileReader("E:\\Abhishek\\hashcode\\hashcode_2022\\input\\" + inputFile);
            BufferedReader br = new BufferedReader(fr);

            StringBuffer sb = new StringBuffer();
            String line = null;

            int count = 0;

            int projectCount = 0;
            int contributorCount = 0;
            int contributorCountVal = 0;

            Contributor contributor = null;
            int skillCount = 0;
            List<Skill> contributorSkills = new ArrayList<>();

            while ((line = br.readLine()) != null) {
//            for (int i = 0; i < contributorCount; i++) {
                sb.append(line);


                String[] split = line.split(" ");
                if (count == 0) {  // is first line assign data to the variables............
                    contributorCount = Integer.valueOf(split[0]);
                    projectCount = Integer.valueOf(split[1]);
                } else {

                    String[] row = line.split(" ");
//                    System.out.println("row -- " + row.length);

                    if(contributorCount != contributorCountVal) {

                        if (contributor == null) {
                            String contributorName = row[0];
//                        skillCount = Integer.parseInt(row[1]);
                            contributor = new Contributor(contributorName, Integer.parseInt(row[1]));
                        } else {
//                        System.out.println("skillCount == contributor.getSkillCount() -- " + (skillCount == contributor.getSkillCount()));

                            if (skillCount == contributor.getSkillCount()) {
                                contributor.setSkills(contributorSkills);
//                            if(!contributorList.contains(contributor)){
                                contributorList.add(contributor);
                                contributorCountVal++;
                                contributor = null;
                                skillCount = 0;
                                contributorSkills.clear();
//                            }
                            } else {
                                contributorSkills.add(new Skill(row[0], Integer.parseInt(row[1])));
                                skillCount++;
                                if (skillCount == contributor.getSkillCount()) {
                                    contributor.setSkills(contributorSkills);
                                    contributorList.add(contributor);
                                    contributorCountVal++;
                                    contributor = null;
                                    skillCount = 0;
                                    contributorSkills.clear();
                                }
                            }
                        }

                    }


//                    if(row.length == 2) {
//                        String contributor = row[0];
//                        int skillCount = Integer.parseInt(row[1]);
//
//                        System.out.println(contributor + " | " + skillCount);
//
//                    }

                }

                count++;
            }

            System.out.println("projectCount --- " + projectCount);
            System.out.println("contributorCount --- " + contributorCount);
//
//            System.out.println(likes);
//            System.out.println(disLikes);

//            writeFile(likes, outputFile);

            System.out.println("contributorList -- " + contributorList.size());

            for (Contributor contributor1 : contributorList){
                System.out.println(contributor1.toString() + " | " +contributor1.getSkills().size());
                System.out.println(contributor1.getSkills().toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void addContributor(Contributor contributor) {
        contributorList.add(contributor);
    }


    public static void writeFile(HashSet<String> likes, String outputFile) {
        try {
            File myObj = new File("E:\\Abhishek\\hashcode\\hashcode_2022\\output\\" + outputFile);
            if (myObj.createNewFile()) {
                FileWriter myWriter = new FileWriter(myObj);

                myWriter.write(likes.size() + " ");
                for (String s : likes) {
                    myWriter.write(s + " ");
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

}
