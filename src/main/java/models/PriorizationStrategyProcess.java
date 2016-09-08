package models;

import algorithm.Request;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PriorizationStrategyProcess {

    private List<Center> priorizationSort(List<Center> centers, Request request) {
        List<Center> centerSort = new ArrayList<>();
        for (Center centerFirst : centers) {
            centerSort.addAll(request.getCenters().stream()
                    .filter(centerSecond -> centerFirst.getWareHouse().equals(centerSecond.getWareHouse()))
                    .collect(Collectors.toList()));
        }
        return centerSort;
    }
    private List<Center> removeSetCenter(Request request) {
        Map<String, Integer> sum = request.getCenters().stream().collect(
                Collectors.groupingBy(Center::getWareHouse, Collectors.summingInt(Center::getQuantity)));
        return sum.entrySet().stream().map(b -> new Center(b.getKey(), null, b.getValue())).collect(Collectors.toList());
    }

    private List<Center> priorizationLargestInventory(Request request) {
        List<Center> centers;
        final Comparator<Center> comp = (p1, p2) -> Integer.compare(p2.getQuantity(), p1.getQuantity());
        centers = removeSetCenter(request).stream().sorted(comp).collect(Collectors.toList());
        return priorizationSort(centers, request);
    }

    private List<Center> priorizationShortestInventory(Request request) {
        List<Center> centers;
        final Comparator<Center> comp = (p1, p2) -> Integer.compare(p1.getQuantity(), p2.getQuantity());
        centers = removeSetCenter(request).stream().sorted(comp).collect(Collectors.toList());
        return priorizationSort(centers, request);
    }

    private List<Center> priorizationLargestCapacityInventory(Request request, List<DistributionCenter> distributionCenters) {
        final Comparator<DistributionCenter> compD = (p1, p2) -> Integer.compare(p1.getCapacity(), p2.getCapacity());
        List<Center> centers;
        List<Center> newCenter = new ArrayList<>();
        Center ce;
        for (Product product : request.getProducts()) {
            centers = request.getCenters()
                    .stream()
                    .filter(c -> c.getProduct().equals(product.getName()))
                    .collect(Collectors.toList());
            ce = centers.stream()
                    .filter(c -> distributionCenters
                            .stream()
                            .filter((d) -> d.getCenter().equals(c.getWareHouse()))
                            .max(compD)
                            .get()
                            .getCenter().equals(c.getWareHouse()))
                    .findFirst()
                    .get();
            newCenter.add(ce);
        }
        return newCenter;
    }

    public List<Center> newCentersPriorization(Request request, List<DistributionCenter> distributionCenters) {
        if (request.getPriorizationStrategy().equals(PriorizationStrategy.LargestInventory)) {
            return priorizationLargestInventory(request);
        } else if (request.getPriorizationStrategy().equals(PriorizationStrategy.ShortestInventory)) {
            return priorizationShortestInventory(request);
        } else if (request.getPriorizationStrategy().equals(PriorizationStrategy.LargestCapacity)) {
            return priorizationLargestCapacityInventory(request, distributionCenters);
        }

        return request.getCenters();
    }
}
