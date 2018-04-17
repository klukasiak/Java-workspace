import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		boolean login = false;
		Scanner sc = new Scanner(System.in);
		DBController controller = new DBController();
		while (!login) {
			System.out.println("Podaj nazwe uzytkownika");
			controller.setUsername(sc.nextLine());
			System.out.println("Podaj haslo");
			controller.setPassword(sc.nextLine());
			login = controller.connectToDatabase();
		}
		
		boolean exit = false;
		
		while (!exit) {
			printMenu();
			switch (sc.nextInt()) {
			case 1:
				controller.showAllRecords();
				break;
			case 2:
				controller.addNewUser();
				break;
			case 3:
				controller.editUser();
				break;
			case 4:
				controller.removeUser();
				break;
			case 5:
				exit = true;
				break;
			default:
				System.out.println("Zly wybor");
			}
		}
		
		sc.close();
	}

	public static void printMenu() {
		System.out.println();
		System.out.println("Wybierz co chcesz teraz zrobic?");
		System.out.println("1. Wypisz wszystkich uzytkownikow");
		System.out.println("2. Dodaj uzytkownika");
		System.out.println("3. Edytowac uzytkownika");
		System.out.println("4. Usunac uzytkownika");
		System.out.println("5. Wyjdz z programu");
		System.out.println();
	}

}