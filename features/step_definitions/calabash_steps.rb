require 'calabash-android/calabash_steps'

Then(/^I press the button "(.*?)"$/) do |text|
  tap_when_element_exists("android.widget.Button {text CONTAINS[c] '#{text}'}")
end

Then(/^I press the text view "([^\"]*)"$/) do |text|
    tap_when_element_exists("* text:'#{text}'")
end

And(/^I press the sign up button/) do
  query_string ="android.support.v7.widget.AppCompatButton id:'signUpBtn'"
  wait_for(:timeout => 2) {element_exists(query_string)}
  btn_action = query(query_string).first
  touch(btn_action)
end

And(/^I press the sign in button/) do
  query_string ="android.support.v7.widget.AppCompatButton id:'signInBtn'"
  wait_for(:timeout => 2) {element_exists(query_string)}
  btn_action = query(query_string).first
  touch(btn_action)
end

Then /^I hide the keyboard$/ do
hide_soft_keyboard()
end