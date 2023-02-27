package by.htp.ex.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.ex.controller.impl.DoSignIn;
import by.htp.ex.controller.impl.DoSignOut;
import by.htp.ex.controller.impl.GoToBasePage;
import by.htp.ex.controller.impl.GoToNewsList;
import by.htp.ex.controller.impl.GoToRegistrationPageCommand;
import by.htp.ex.controller.impl.GoToViewNews;
import by.htp.ex.controller.impl.DoRegistration;
import by.htp.ex.controller.impl.ReturnToBasePage;
import by.htp.ex.controller.impl.DoChangeLocal;
import by.htp.ex.controller.impl.GoToEditNews;
import by.htp.ex.controller.impl.DoEdit;
import by.htp.ex.controller.impl.DoDeliteNews;
import by.htp.ex.controller.impl.GoToAddNews;
import by.htp.ex.controller.impl.DoAddNews;
import by.htp.ex.controller.impl.DoDeleteSelectedNews;
import by.htp.ex.controller.impl.NoSuchFunction;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(CommandName.GO_TO_BASE_PAGE, new GoToBasePage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
		commands.put(CommandName.DO_SIGN_IN, new DoSignIn());
		commands.put(CommandName.DO_SIGN_OUT, new DoSignOut());
		commands.put(CommandName.GO_TO_NEWS_LIST, new GoToNewsList());
		commands.put(CommandName.GO_TO_VIEW_NEWS, new GoToViewNews());
		commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
		commands.put(CommandName.RETURN_TO_BASE_PAGE, new ReturnToBasePage());
		commands.put(CommandName.DO_CHANGE_LOCAL, new DoChangeLocal());
		commands.put(CommandName.GO_TO_EDIT_NEWS, new GoToEditNews());
		commands.put(CommandName.DO_EDIT_NEWS, new DoEdit());
		commands.put(CommandName.DO_DELETE_NEWS, new DoDeliteNews());
		commands.put(CommandName.GO_TO_ADD_NEWS, new GoToAddNews());
		commands.put(CommandName.DO_ADD_NEWS, new DoAddNews());
		commands.put(CommandName.DO_DELETE_SELECTED_NEWS, new DoDeleteSelectedNews());
		commands.put(CommandName.NO_SUCH_FUNCTION, new NoSuchFunction());
	}

	public Command getCommand(String name) {
		try {
			CommandName commandName = CommandName.valueOf(name.toUpperCase());
			Command command = commands.get(commandName);
			return command;
		} catch (Exception e) {
			return commands.get(CommandName.NO_SUCH_FUNCTION);
		}
	}
}
