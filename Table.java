import java.awt.TextField;

public class Table
{
	TextField[] dishshoworder = new TextField[4];
	TextField[] scoredish = new TextField[4];
	TextField[] dishstatus = new TextField[4];

	int table_number;

	public Table(int _table_number)
	{
		table_number = _table_number;
	}

	final int size = 35;
	boolean[] order_status = new boolean[size];
	boolean[] scoreboard_status = new boolean[4];
}