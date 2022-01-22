import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;
import java.util.stream.IntStream;

class Platform{
    String plat_name, parent_comp,country;
    long monthly_active_users;

    public Platform(String plat_name, String parent_comp, String country, long monthly_active_users){
        this.plat_name = plat_name;
        this.parent_comp = parent_comp;
        this.country = country;
        this.monthly_active_users = monthly_active_users;
    }

    public String get_plat_name(){
        return plat_name;
    }

    public void set_plat_name(String plat_name){
        this.plat_name = plat_name;
    }

    public String get_parent_comp(){
        return parent_comp;
    }

    public void set_parent_comp(String parent_comp){
        this.parent_comp = parent_comp;
    }

    public String get_country(){
        return country;
    }

    public void set_country(String country){
        this.country = country;
    }

    public Long get_monthly_active_users() {
		return monthly_active_users;
	}

	public void set_monthly_active_users(long monthly_active_users) {
		this.monthly_active_users = monthly_active_users;
    }

    @Override
	public String toString() {
		return "Platform [Name = " + plat_name + ", Parent Company = " + parent_comp + ", Country = " + country + ", Monthly Active user = " + monthly_active_users + "]";
	}
}

class StreamImpl{

    public static void main(String args[]){

        List<Platform> plat = new ArrayList<Platform>();
        plat.add(new Platform("Facebook","Meta","US",2910));
        plat.add(new Platform("WhatsApp","Meta","US",2000));
        plat.add(new Platform("TikTok","Bytedance","China",1000));
        plat.add(new Platform("Youtube","Alphabet","US",2291));
        plat.add(new Platform("Messenger","Meta","US",1300));
        plat.add(new Platform("Instagram","Meta","US",1287));
        plat.add(new Platform("Telegram","Telegram","UAE",600));
        plat.add(new Platform("WeChat","Tencent","China",1225));

        //Least popular social media platform
        plat.stream()
        .sorted((plat1, plat2) -> ((Long)plat1.get_monthly_active_users()).compareTo(plat2.get_monthly_active_users()))
        .limit(1).forEach(platform -> {
            System.out.println("Platform with least active monthly users");
            System.out.println("Platform Name: "+platform.get_plat_name());
            System.out.println("Monthly active users: "+ platform.get_monthly_active_users() +"M");
        });

        System.out.println();
        System.out.println("---------------------------------------");
        System.out.println();
        
        //Most popular social media platform
        plat.stream()
        .sorted((plat1, plat2) -> plat1.get_monthly_active_users()<plat2.get_monthly_active_users()?1:-1)
        .limit(1).forEach(platform -> {
            System.out.println("Platform with most active monthly users");
            System.out.println("Platform Name: "+platform.get_plat_name());
            System.out.println("Monthly active users: "+ platform.get_monthly_active_users()+"M");
        });

        System.out.println();
        System.out.println("---------------------------------------");
        System.out.println();

        //Platforms with more than or equal to 2000 active monthly users
        System.out.println("Platform with more than or equal to 2000 active users");
        List<Platform> popular = plat.stream()
                                 .filter(p -> p.get_monthly_active_users()>=2000)
                                 .collect(Collectors.toList());
        popular.forEach(p->System.out.println(p));

        System.out.println();
        System.out.println("---------------------------------------");
        System.out.println();

        //Platforms with country of origin as US
        System.out.println("Platforms with country of origin as US");
        plat.stream()
        .filter(p -> p.get_country().equalsIgnoreCase("US"))
        .map(s -> s.get_plat_name())
        .forEach(n -> System.out.println(n));

        System.out.println();
        System.out.println("---------------------------------------");
        System.out.println();

        //find parent company of all platforms
        System.out.println("Unique Parent companies");
        plat.stream()
        .map(p -> p.get_parent_comp())
        .distinct()
        .forEach(n -> System.out.println(n));

        System.out.println();
        System.out.println("---------------------------------------");
        System.out.println();

        //find distinct countries
        System.out.println("Unique Countries");
        plat.stream()
        .map(p -> p.get_country())
        .distinct()
        .forEach(n -> System.out.println(n));

        System.out.println();
        System.out.println("---------------------------------------");
        System.out.println();

        // countrywise platforms
        System.out.println("Count of platform countrywise");
        long us = plat.stream()
        .filter(p -> p.get_country().equalsIgnoreCase("US"))
        .count();
        System.out.println("US: "+us);
        long china = plat.stream()
        .filter(p -> p.get_country().equalsIgnoreCase("China"))
        .count();
        System.out.println("China: "+china);
        long uae = plat.stream()
        .filter(p -> p.get_country().equalsIgnoreCase("UAE"))
        .count();
        System.out.println("UAE: "+uae);

        System.out.println();
        System.out.println("---------------------------------------");
        System.out.println();

        IntSummaryStatistics stats = plat.stream()
            .map(p->p.get_monthly_active_users())
            .mapToInt(Long::intValue)
            .summaryStatistics();

        System.out.println("Average users using social media platforms actively : "+stats.getAverage()+"M");
         
    }
}