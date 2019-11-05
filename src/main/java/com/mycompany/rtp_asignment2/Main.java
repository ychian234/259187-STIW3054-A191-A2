package com.mycompany.rtp_asignment2;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {

    private static final List<TempData1> loginId = new ArrayList();
    private static final List<TempData1> repoUrl = new ArrayList();
    private static final List<TempData1> followerUrl = new ArrayList();
    private static final List<TempData1> followingUrl = new ArrayList();
    private static final List<TempData1> subscriptionUrl = new ArrayList();
    private static final List<TempData2> repoTotal = new ArrayList();
    private static final List<TempData2> followerTotal = new ArrayList();
    private static final List<TempData2> followingTotal = new ArrayList();
    private static final List<TempData2> subscriptionTotal = new ArrayList();

    public static void getGitData() {
        try {
            for (int i = 1; i <= 2; i++) {
                HttpURLConnection httpcon = (HttpURLConnection) new URL("https://api.github.com/users/zhamri/followers?per_page=100&page=" + i + "&&access_token=33441216c5b8931adebe246c9852796adc39844e").openConnection();
                httpcon.addRequestProperty("User-Agent", "Mozilla/5.0");
                BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
                StringBuilder responseSB = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    responseSB.append("\n" + line);
                }
                in.close();
                Arrays.stream(responseSB.toString().split("\"login\":\"")).skip(1).map(l -> l.split("\",")[0]).forEach(l -> loginId.add(new TempData1(l)));
                Arrays.stream(responseSB.toString().split("\"repos_url\":\"")).skip(1).map(l1 -> l1.split("\",")[0]).forEach(l1 -> repoUrl.add(new TempData1(l1)));
                Arrays.stream(responseSB.toString().split("\"followers_url\":\"")).skip(1).map(l2 -> l2.split("\",")[0]).forEach(l2 -> followerUrl.add(new TempData1(l2)));
                Arrays.stream(responseSB.toString().split("\"following_url\":\"")).skip(1).map(l3 -> l3.split("\\{/other_user}\",")[0]).forEach(l3 -> followingUrl.add(new TempData1(l3)));
                Arrays.stream(responseSB.toString().split("\"subscriptions_url\":\"")).skip(1).map(l4 -> l4.split("\",")[0]).forEach(l4 -> subscriptionUrl.add(new TempData1(l4)));
            }
            System.out.println("Follower's id has been collected successfully.");
        } catch (IOException e) {
            System.out.println("ERROR : Failed to access zhamri's followers ");
        }
    }

    public static void getNoOfRepos() {
        try {
            for (int p = 0; p < repoUrl.size(); p++) {
                long total1 = 0;
                int i = 1;
                do {
                    HttpURLConnection httpcon2 = (HttpURLConnection) new URL(repoUrl.get(p).getData0() + "?per_page=100&&page=" + i + "&&access_token=33441216c5b8931adebe246c9852796adc39844e").openConnection();
                    httpcon2.addRequestProperty("User-Agent", "Mozilla/5.0");
                    BufferedReader in2 = new BufferedReader(new InputStreamReader(httpcon2.getInputStream()));
                    StringBuilder responseSB2 = new StringBuilder();
                    String line2;
                    while ((line2 = in2.readLine()) != null) {
                        responseSB2.append("\n" + line2);
                    }
                    in2.close();
                    total1 += Arrays.stream(responseSB2.toString().split("\"full_name\":\"")).skip(1).map(l2 -> l2.split("\",")[0]).count();
                    i++;
                } while (total1 == 100 || total1 == 200 || total1 == 300 || total1 == 400 || total1 == 500 || total1 == 600 || total1 == 700 || total1 == 800 || total1 == 900 || total1 == 1000);
                repoTotal.add(new TempData2(total1));
            }
            System.out.println("Follower's total repos count has been collected successfully.");
        } catch (IOException e) {
            System.out.println("ERROR : Failed to access no. of repos");
        }
    }

    public static void getNoOfFollowers() {
        try {
            for (int p = 0; p < followerUrl.size(); p++) {
                long total1 = 0;
                int i = 1;
                do {
                    HttpURLConnection httpcon2 = (HttpURLConnection) new URL(followerUrl.get(p).getData0() + "?per_page=100&&page=" + i + "&&access_token=33441216c5b8931adebe246c9852796adc39844e").openConnection();
                    httpcon2.addRequestProperty("User-Agent", "Mozilla/5.0");
                    BufferedReader in2 = new BufferedReader(new InputStreamReader(httpcon2.getInputStream()));
                    StringBuilder responseSB2 = new StringBuilder();
                    String line2;
                    while ((line2 = in2.readLine()) != null) {
                        responseSB2.append("\n" + line2);
                    }
                    in2.close();
                    total1 += Arrays.stream(responseSB2.toString().split("\"avatar_url\":\"")).skip(1).map(l2 -> l2.split("\",")[0]).count();
                    i++;
                } while (total1 == 100 || total1 == 200 || total1 == 300 || total1 == 400 || total1 == 500 || total1 == 600 || total1 == 700 || total1 == 800 || total1 == 900 || total1 == 1000);
                followerTotal.add(new TempData2(total1));
            }
            System.out.println("Follower's total followers count has been collected successfully.");
        } catch (IOException e) {
            System.out.println("ERROR : Failed to access no. of followers");
        }
    }

    public static void getNoOfFollowing() {
        try {
            for (int p = 0; p < followingUrl.size(); p++) {
                long total1 = 0;
                int i = 1;
                do {
                    HttpURLConnection httpcon2 = (HttpURLConnection) new URL(followingUrl.get(p).getData0() + "?per_page=100&&page=" + i + "&&access_token=33441216c5b8931adebe246c9852796adc39844e").openConnection();
                    httpcon2.addRequestProperty("User-Agent", "Mozilla/5.0");
                    BufferedReader in2 = new BufferedReader(new InputStreamReader(httpcon2.getInputStream()));
                    StringBuilder responseSB2 = new StringBuilder();
                    String line2;
                    while ((line2 = in2.readLine()) != null) {
                        responseSB2.append("\n" + line2);
                    }
                    in2.close();
                    total1 += Arrays.stream(responseSB2.toString().split("\"avatar_url\":\"")).skip(1).map(l2 -> l2.split("\",")[0]).count();
                    i++;
                } while (total1 == 100 || total1 == 200 || total1 == 300 || total1 == 400 || total1 == 500 || total1 == 600 || total1 == 700 || total1 == 800 || total1 == 900 || total1 == 1000
                        || total1 == 1100 || total1 == 1200 || total1 == 1300 || total1 == 1400 || total1 == 1500 || total1 == 1600 || total1 == 1700 || total1 == 1800 || total1 == 1900 || total1 == 2000
                        || total1 == 2100 || total1 == 2200 || total1 == 2300 || total1 == 2400 || total1 == 2500 || total1 == 2600 || total1 == 2700 || total1 == 2800 || total1 == 2900 || total1 == 3000
                        || total1 == 3100 || total1 == 3200 || total1 == 3300 || total1 == 3400 || total1 == 3500 || total1 == 3600 || total1 == 3700 || total1 == 3800 || total1 == 3900 || total1 == 4000
                        || total1 == 4100 || total1 == 4200 || total1 == 4300 || total1 == 4400 || total1 == 4500 || total1 == 4600 || total1 == 4700 || total1 == 4800 || total1 == 4900 || total1 == 5000
                        || total1 == 5100 || total1 == 5200 || total1 == 5300 || total1 == 5400 || total1 == 5500 || total1 == 5600 || total1 == 5700 || total1 == 5800 || total1 == 5900 || total1 == 6000
                        || total1 == 6100 || total1 == 6200 || total1 == 6300 || total1 == 6400 || total1 == 6500 || total1 == 6600 || total1 == 6700 || total1 == 6800 || total1 == 6900 || total1 == 7000);
                followingTotal.add(new TempData2(total1));
            }
            System.out.println("Follower's total following count has been collected successfully.");
        } catch (IOException e) {
            System.out.println("ERROR : Failed to access no. of following");
        }
    }

    public static void getNoOfSubscription() {
        try {
            for (int p = 0; p < subscriptionUrl.size(); p++) {
                long total1 = 0;
                int i = 1;
                do {
                    HttpURLConnection httpcon2 = (HttpURLConnection) new URL(subscriptionUrl.get(p).getData0() + "?per_page=100&&page=" + i + "&&access_token=33441216c5b8931adebe246c9852796adc39844e").openConnection();
                    httpcon2.addRequestProperty("User-Agent", "Mozilla/5.0");
                    BufferedReader in2 = new BufferedReader(new InputStreamReader(httpcon2.getInputStream()));
                    StringBuilder responseSB2 = new StringBuilder();
                    String line2;
                    while ((line2 = in2.readLine()) != null) {
                        responseSB2.append("\n" + line2);
                    }
                    in2.close();
                    total1 += Arrays.stream(responseSB2.toString().split("\"full_name\":\"")).skip(1).map(l2 -> l2.split("\",")[0]).count();
                    i++;
                } while (total1 == 100 || total1 == 200 || total1 == 300 || total1 == 400 || total1 == 500 || total1 == 600 || total1 == 700 || total1 == 800 || total1 == 900 || total1 == 1000);
                subscriptionTotal.add(new TempData2(total1));
            }
            System.out.println("Follower's total subscription count has been collected successfully.");
        } catch (IOException e) {
            System.out.println("ERROR : Failed to access no. of subscription");
        }
    }

    public static void writeToExcel() {
        if (loginId.isEmpty() || repoTotal.isEmpty() || followerTotal.isEmpty() || followingTotal.isEmpty() || subscriptionTotal.isEmpty()) {
            System.out.println("ERROR : No data to write, build terminated.");
            System.exit(0);
        }
        String excelFile = "Zhamri_followers.xlsx";
        System.out.println("Writing the " + excelFile + "...");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Followers");
        try {
            int k = 0;
            do {
                if (k == 0) {
                    XSSFRow row = sheet.createRow(k);
                    XSSFCell cell1_1 = row.createCell(0);
                    cell1_1.setCellValue("No.");
                    XSSFCell cell1_2 = row.createCell(1);
                    cell1_2.setCellValue("login id");
                    XSSFCell cell1_3 = row.createCell(2);
                    cell1_3.setCellValue("Number of repositories");
                    XSSFCell cell1_4 = row.createCell(3);
                    cell1_4.setCellValue("Number of followers");
                    XSSFCell cell1_5 = row.createCell(4);
                    cell1_5.setCellValue("Number of following");
                    XSSFCell cell1_6 = row.createCell(5);
                    cell1_6.setCellValue("Number of subscriptions");
                    k++;
                } else {
                    int l = 1;
                    for (int i = 0; i < loginId.size(); i++) {
                        XSSFRow row = sheet.createRow(l);
                        XSSFCell cell2_1 = row.createCell(0);
                        cell2_1.setCellValue(l);
                        XSSFCell cell2_2 = row.createCell(1);
                        cell2_2.setCellValue(loginId.get(i).getData0());
                        XSSFCell cell2_3 = row.createCell(2);
                        cell2_3.setCellValue(repoTotal.get(i).getData0());
                        XSSFCell cell2_4 = row.createCell(3);
                        cell2_4.setCellValue(followerTotal.get(i).getData0());
                        XSSFCell cell2_5 = row.createCell(4);
                        cell2_5.setCellValue(followingTotal.get(i).getData0());
                        XSSFCell cell2_6 = row.createCell(5);
                        cell2_6.setCellValue(subscriptionTotal.get(i).getData0());
                        l++;
                    }
                    k++;
                }
            } while (k < 2);
            FileOutputStream outputFile = new FileOutputStream(excelFile);
            workbook.write(outputFile);
            outputFile.flush();
            outputFile.close();
            System.out.println(excelFile + " Is written successfully.");
        } catch (IOException e) {
            System.out.println("ERROR : Failed to write the file!");
        }
    }

    public static void main(String[] args) throws Exception {
        getGitData();
        getNoOfRepos();
        getNoOfFollowers();
        getNoOfFollowing();
        getNoOfSubscription();
        writeToExcel();
    }
}