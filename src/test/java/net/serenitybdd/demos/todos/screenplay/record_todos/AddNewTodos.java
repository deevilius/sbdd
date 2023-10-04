package net.serenitybdd.demos.todos.screenplay.record_todos;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.demos.todos.screenplay.questions.TheItems;
import net.serenitybdd.demos.todos.screenplay.tasks.AddATodoItem;
import net.serenitybdd.demos.todos.screenplay.tasks.Start;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.questions.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

/**
 * This example illustrates using Serenity Steps with JUnit.
 */
@ExtendWith(SerenityJUnit5Extension.class)
@Tag("Screenplay")
public class AddNewTodos {
    
    private Actor james = Actor.named("James");
    @Managed private WebDriver hisBrowser;
    
    @BeforeEach
    public void jamesCanBrowseTheWeb() {
        james.can(BrowseTheWeb.with(hisBrowser));
    }
    
    @Test
    public void should_be_able_to_add_the_first_todo_item() {
        givenThat(james).wasAbleTo(Start.withAnEmptyTodoList());
        when(james).attemptsTo(AddATodoItem.called("Buy some milk"));
        then(james).should(seeThat(TheItems.displayed(), hasItem("Buy some milk")));
    }
    
    @Test
    public void should_be_able_to_add_additional_todo_items() {
        givenThat(james).wasAbleTo(Start.withATodoListContaining("Walk the dog", "Put out the garbage"));
        when(james).attemptsTo(AddATodoItem.called("Buy some milk"));
        then(james).should(seeThat(TheItems.displayed(), hasItems("Walk the dog", "Put out the garbage", "Buy some milk")));
    }
    
    
}