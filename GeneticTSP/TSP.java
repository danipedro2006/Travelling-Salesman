package tsp;

public class TSP {
public static void main(String[] args) {
	//Create cities
	int numCities=100;
	City cities[]=new City[numCities];
	for(int cityIndex=0;cityIndex<numCities;cityIndex++) {
		//Generate x, y position
		
		int xPos=(int) (100*Math.random());
		int yPos=(int) (100*Math.random());
		
		//Add city
		
		cities[cityIndex]=new City(xPos, yPos);
	}
	
	
	GeneticAlgorithm ga=new GeneticAlgorithm(50,0,0.9,2,5);
	Population population=ga.initPopulation(cities.length);
	//Evaluate population
	ga.evalPopulation(population, cities);
	Route startRoute=new Route(population.getFittest(0),cities);
	System.out.println("Start Distance: "+startRoute.getDistance());
	
	int generation=1;
	//Start evolution loop
	
	while(ga.isTerminationConditionmet(generation, 250)==false) {
		Route route=new Route(population.getFittest(0),cities);
		System.out.println("Best distance:"+ route.getDistance());
		
		//Apply crossover
		population=ga.orderedCrossover(population);
		//Apply mutation
		
		population=ga.mutatePopulation(population);
		//Evaluate population
		ga.evalPopulation(population, cities);
		//Increment current generation
		generation++;
	}
}
}
