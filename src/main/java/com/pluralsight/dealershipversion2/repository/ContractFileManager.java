package com.pluralsight.dealershipversion2.repository;

import com.pluralsight.dealershipversion2.entity.Car.Car;
import com.pluralsight.dealershipversion2.entity.Dealer;
import com.pluralsight.dealershipversion2.entity.document.Contract;
import com.pluralsight.dealershipversion2.entity.document.LeaseContract;
import com.pluralsight.dealershipversion2.entity.document.SalesContract;
import com.pluralsight.dealershipversion2.utils.InputOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ContractFileManager {
        private static final String FILE_NAME = "inventory.csv";

        public static Dealer getDealer() {
            List<Contract> contractList = new ArrayList<>();
            Dealer dealer = dealer = new Dealer(contractList);;
            try(BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    Contract contract = createContract(line);
                    contractList.add(contract);
                }
            }  catch (Exception e) {
                e.printStackTrace();
            }

            InputOutput.formatOutput("You have successfully read data from file:  " + FILE_NAME + "\nTotal of Contract is: " + contractList.size());
            contractList.forEach(System.out::println);
            return dealer;
        }

        public static void updateAndSaveDealer(Dealer Dealer, String filename) {
            List<Contract> contractList = Dealer.getContractList();
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))){

                for (Contract contract: contractList) {
                    writeContract(contract, bufferedWriter);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            InputOutput.formatOutput("You have successfully update data and save from file:  " + FILE_NAME + "\nTotal of vehicles is: " + contractList.size());

        }


    public static Contract createContract(String contractDetails) {
        StringTokenizer tokenizer = new StringTokenizer(contractDetails, "|");
        Contract contract = null;

        if (!(tokenizer.nextToken().equalsIgnoreCase("SALE"))) {
            tokenizer.nextToken(); // Skip "LEASE"

            String date = tokenizer.nextToken();
            String customerName = tokenizer.nextToken();
            String customerEmail = tokenizer.nextToken();

//          10112|1993|Ford|Explorer|SUV|Red|525123|995.00
            int vinNumber = Integer.parseInt(tokenizer.nextToken());
            int year = Integer.parseInt(tokenizer.nextToken());
            String make = tokenizer.nextToken();
            String model = tokenizer.nextToken();
            String type = tokenizer.nextToken();
            String color = tokenizer.nextToken();
            int odometer = Integer.parseInt(tokenizer.nextToken());
            double price = Double.parseDouble(tokenizer.nextToken());
            Car car = new Car(vinNumber, year, make, model, type, color, odometer, price);

//          ending value | processing fee| total price | monthly payment
            double endingValue = Double.parseDouble(tokenizer.nextToken());
            double processingFee = Double.parseDouble(tokenizer.nextToken());
            double totalPrice = Double.parseDouble(tokenizer.nextToken());
            double monthlyPayment = Double.parseDouble(tokenizer.nextToken());
            contract = new LeaseContract(date, customerName, customerEmail, car, endingValue, processingFee, totalPrice, monthlyPayment);
        } else {
            String date = tokenizer.nextToken();
            String customerName = tokenizer.nextToken();
            String customerEmail = tokenizer.nextToken();

            int vinNumber = Integer.parseInt(tokenizer.nextToken());
            int year = Integer.parseInt(tokenizer.nextToken());
            String make = tokenizer.nextToken();
            String model = tokenizer.nextToken();
            String type = tokenizer.nextToken();
            String color = tokenizer.nextToken();
            int odometer = Integer.parseInt(tokenizer.nextToken());
            double price = Double.parseDouble(tokenizer.nextToken());
            Car car = new Car(vinNumber, year, make, model, type, color, odometer, price);


//          SALE_TAX | RECORDING_FEE | processingFee |  total price | isFinance | monthly payment
            double saleTax = Double.parseDouble(tokenizer.nextToken());
            double recordingFee = Double.parseDouble(tokenizer.nextToken());
            double processingFee = Double.parseDouble(tokenizer.nextToken());
            double totalPrice = Double.parseDouble(tokenizer.nextToken());
            boolean isFinance = Boolean.parseBoolean(tokenizer.nextToken());
            double monthlyPayment = Double.parseDouble(tokenizer.nextToken());

            contract = new SalesContract(date, customerName, customerEmail, car, saleTax, recordingFee, processingFee, totalPrice, isFinance, monthlyPayment);
        }

        return contract ;
    }


    public static String formatSalesContract(SalesContract salesContract) {
        return new StringBuilder()
                .append("SALE|")
                .append(salesContract.getDate()).append("|")
                .append(salesContract.getName()).append("|")
                .append(salesContract.getEmail()).append("|")
                .append(salesContract.getCarSold().getVin()).append("|")
                .append(salesContract.getCarSold().getYear()).append("|")
                .append(salesContract.getCarSold().getMake()).append("|")
                .append(salesContract.getCarSold().getModel()).append("|")
                .append(salesContract.getCarSold().getVehicleType()).append("|")
                .append(salesContract.getCarSold().getColor()).append("|")
                .append(salesContract.getCarSold().getOdometer()).append("|")
                .append(String.format("%.2f", salesContract.getCarSold().getPrice())).append("|")
                .append(String.format("%.2f", salesContract.getSaleTax())).append("|")
                .append(String.format("%.2f", salesContract.getRecordingFee())).append("|")
                .append(String.format("%.2f", salesContract.getProceesingFee())).append("|")
                .append(String.format("%.2f", salesContract.getTotalPrice())).append("|")
                .append(salesContract.isFinance() ? "YES" : "NO").append("|")
                .append(String.format("%.2f", salesContract.getMonthlypayment()))
                .toString();
    }

    public static String formatLeaseContract(LeaseContract leaseContract) {
        return new StringBuilder()
                .append("LEASE|")
                .append(leaseContract.getDate()).append("|")
                .append(leaseContract.getName()).append("|")
                .append(leaseContract.getEmail()).append("|")
                .append(leaseContract.getCarSold().getVin()).append("|")
                .append(leaseContract.getCarSold().getYear()).append("|")
                .append(leaseContract.getCarSold().getMake()).append("|")
                .append(leaseContract.getCarSold().getModel()).append("|")
                .append(leaseContract.getCarSold().getVehicleType()).append("|")
                .append(leaseContract.getCarSold().getColor()).append("|")
                .append(leaseContract.getCarSold().getOdometer()).append("|")
                .append(String.format("%.2f", leaseContract.getCarSold().getPrice())).append("|")
                .append(String.format("%.2f", leaseContract.getExceptedEndingValue())).append("|")
                .append(String.format("%.2f", leaseContract.getLeaseFee())).append("|")
                .append(String.format("%.2f", leaseContract.getTotalPrice())).append("|")
                .append(String.format("%.2f", leaseContract.getMonthlypayment()))
                .toString();
    }

    public static void writeContract(Contract contract, BufferedWriter bufferedWriter) throws IOException {
        if (contract instanceof SalesContract salesContract) {
            bufferedWriter.write(formatSalesContract(salesContract));
        } else if (contract instanceof LeaseContract leaseContract) {
            bufferedWriter.write(formatLeaseContract(leaseContract));
        }
        bufferedWriter.newLine();
    }

}
