package org.home.gat.command;

import org.springframework.context.ApplicationContext;

public interface GatCommand {

    void run(ApplicationContext applicationContext);

}
