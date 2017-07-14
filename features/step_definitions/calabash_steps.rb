require 'calabash-android/calabash_steps'

Then /^I press the text view "([^\"]*)"$/ do |text|
    tap_when_element_exists("* text:'#{text}'")
end

Then /^I hide the keyboard$/ do
hide_soft_keyboard()
end